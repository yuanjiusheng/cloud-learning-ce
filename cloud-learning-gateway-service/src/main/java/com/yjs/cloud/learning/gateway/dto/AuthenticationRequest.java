package com.yjs.cloud.learning.gateway.dto;

import lombok.Data;

/**
 * 认证实体
 *
 * @author Andrew.xiao
 * @since 2020/7/29
 */
@Data
public class AuthenticationRequest {
    /**
     * 登录手机号码
     */
    private String mobilePhone;

    /**
     * 认证码
     */
    private String authCode;

    /**
     * 刷新令牌
     */
    private String refreshToken;
}
