package com.yjs.cloud.learning.auth.biz.jwt.service;

import com.yjs.cloud.learning.auth.biz.api.member.MemberApi;
import com.yjs.cloud.learning.auth.biz.api.member.bean.MemberResponse;
import com.yjs.cloud.learning.auth.biz.api.user.UserApi;
import com.yjs.cloud.learning.auth.biz.api.user.bean.UserResponse;
import com.yjs.cloud.learning.auth.biz.jwt.bean.SecurityMemberInfo;
import com.yjs.cloud.learning.auth.biz.jwt.constant.MessageConstant;
import com.yjs.cloud.learning.auth.biz.jwt.constant.Oauth2Constant;
import com.yjs.cloud.learning.auth.biz.authority.entity.Authority;
import com.yjs.cloud.learning.auth.biz.authority.service.AuthorityService;
import com.yjs.cloud.learning.auth.biz.jwt.entity.SecurityUser;
import com.yjs.cloud.learning.auth.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UserDetailsService 实现
 *
 * @author Andrew.xiao
 * @since 2020/7/16
 */
@AllArgsConstructor
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final AuthCodeStoreService authCodeStoreService;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityService authorityService;
    private final UserApi userApi;
    private final MemberApi memberApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String scope = request.getParameter("scope");
        SecurityUser securityUser;
        String grantType = request.getParameter("grant_type");
        // 是否是刷新token
        boolean isRefresh = !StringUtils.isEmpty(grantType) && "refresh_token".equals(grantType);
        if (Oauth2Constant.SCOPE_WEB_CLIENT.equals(scope)) {
            SecurityMemberInfo memberInfo =  new SecurityMemberInfo();
            Long memberId = null;
            if (isRefresh) {
                MemberResponse member = memberApi.getByMobile(username);
                if (member == null) {
                    throw new UsernameNotFoundException(MessageConstant.USERNAME_IS_NOT_FOUND);
                }
                memberId = member.getId();
            } else {
                String authCode = authCodeStoreService.get(username);
                if (authCode == null) {
                    throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
                }
                memberInfo.setAuthCode(passwordEncoder.encode(authCode));
            }
            memberInfo.setId(memberId);
            memberInfo.setMobile(username);
            securityUser = new SecurityUser(memberInfo);
        } else if(Oauth2Constant.SCOPE_WEB_CLIENT_PASSWORD.equals(scope)){
            MemberResponse member = memberApi.getByMobile(username);
            if (member == null) {
                throw new UsernameNotFoundException(MessageConstant.USERNAME_IS_NOT_FOUND);
            }
            List<Authority> authorities = new ArrayList<>();
            UserResponse user = new UserResponse();
            user.setId(member.getId());
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(member.getPassword()));
            securityUser = new SecurityUser(user, mapToGrantedAuthorities(authorities), Oauth2Constant.SCOPE_WEB_CLIENT_PASSWORD);
        } else if(Oauth2Constant.SCOPE_ADMIN_CLIENT.equals(scope)){
            UserResponse user = userApi.getByMobile(username);
            if (user == null) {
                throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
            }
            List<Authority> authorities = authorityService.getByUserId(user.getId());
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            securityUser = new SecurityUser(user, mapToGrantedAuthorities(authorities), Oauth2Constant.SCOPE_ADMIN_CLIENT);
        } else if(Oauth2Constant.SCOPE_SERVICE_CLIENT.equals(scope)){
            String name = "service";
            if (!name.equals(username)) {
                throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
            }
            UserResponse user = new UserResponse();
            user.setId(0L);
            List<Authority> authorities = authorityService.list();
            user.setUsername(username);
            user.setPassword("service_password");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            securityUser = new SecurityUser(user, mapToGrantedAuthorities(authorities), Oauth2Constant.SCOPE_SERVICE_CLIENT);
        } else {
            throw new UnsupportedOperationException();
        }

        if (!securityUser.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUser;
    }

    @EventListener
    public void authSuccessEventListener(AuthenticationSuccessEvent authorizedEvent){
        Object principal = authorizedEvent.getAuthentication().getPrincipal();
        if(principal instanceof SecurityUser){
            SecurityUser securityUser = (SecurityUser)principal;
            if (Oauth2Constant.SCOPE_WEB_CLIENT.equals(securityUser.getScope())) {
                MemberResponse user = memberApi.getByMobile(securityUser.getUsername());
                securityUser.setId(user.getId());
            }
        }
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
    }
}
