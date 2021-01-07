package com.yjs.cloud.learning.usercenter.common.loginuser;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import com.yjs.cloud.learning.usercenter.common.loginuser.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 从Header获取登录用户信息
 *
 * @author Andrew.xiao
 * @since 2020/7/17
 */
@Component
public class LoginUserHolder {

    /**
     * 获取当前登录用户
     * @return 登录用户信息
     */
    public UserDTO getCurrentUser(){
        UserDTO userDTO = new UserDTO();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            String userStr = request.getHeader("user");
            JSONObject userJsonObject = new JSONObject(userStr);
            if (CollectionUtils.isEmpty(userJsonObject)) {
                return userDTO;
            }
            userDTO.setUsername(userJsonObject.getStr("user_name"));
            userDTO.setId(Convert.toLong(userJsonObject.get("id")));
            userDTO.setAuthorities(Convert.toList(String.class,userJsonObject.get("authorities")));
            userDTO.setBearerToken(request.getHeader("Authorization"));
        }
        return userDTO;
    }

}
