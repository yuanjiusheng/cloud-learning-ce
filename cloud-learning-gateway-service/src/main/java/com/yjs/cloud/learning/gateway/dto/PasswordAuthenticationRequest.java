package com.yjs.cloud.learning.gateway.dto;

import lombok.Data;

/**
 * 认证实体
 *
 * @author Andrew.xiao
 * @since 2020/7/29
 */
@Data
public class PasswordAuthenticationRequest {

    /**
     * 账户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 刷新令牌
     */
    private String refreshToken;
}
