package com.yjs.cloud.learning.auth.biz.jwt.entity;

import com.yjs.cloud.learning.auth.biz.api.user.bean.UserResponse;
import com.yjs.cloud.learning.auth.biz.jwt.bean.SecurityMemberInfo;
import com.yjs.cloud.learning.auth.biz.jwt.constant.Oauth2Constant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 登录用户信息
 *
 * @author Andrew.xiao
 * @since 2020/7/16
 */
@Data
public class SecurityUser implements UserDetails {

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 部门id
     */
    private Long departmentId;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 状态
     */
    private Boolean enabled;

    /**
     * 权限
     */
    private Collection<GrantedAuthority>  authorities;

    /**
     * oauth2 scope
     */
    private String scope;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public SecurityUser(SecurityMemberInfo securityMemberInfo) {
        this.setUsername(securityMemberInfo.getMobile());
        this.setPassword(securityMemberInfo.getAuthCode());
        this.setEnabled(true);
        this.setId(securityMemberInfo.getId());
        this.setScope(Oauth2Constant.SCOPE_WEB_CLIENT);
        authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("member"));
    }

    public SecurityUser(UserResponse user, Collection<GrantedAuthority> authorities) {
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setDepartmentId(user.getDepartmentId());
        this.setEnabled(true);
        this.setId(user.getId());
        this.setScope(Oauth2Constant.SCOPE_WEB_CLIENT_PASSWORD);
        this.setAuthorities(authorities);
    }

    public SecurityUser(UserResponse user, Collection<GrantedAuthority> authorities, String scope) {
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setDepartmentId(user.getDepartmentId());
        this.setEnabled(true);
        this.setId(user.getId());
        this.setScope(scope);
        this.setAuthorities(authorities);
    }
}
