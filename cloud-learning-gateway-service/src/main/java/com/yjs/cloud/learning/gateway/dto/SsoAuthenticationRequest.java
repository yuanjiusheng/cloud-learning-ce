package com.yjs.cloud.learning.gateway.dto;

import lombok.Data;

/**
 * 认证实体
 *
 * @author Andrew.xiao
 * @since 2020/7/29
 */
@Data
public class SsoAuthenticationRequest {
    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 客户端秘钥
     */
    private String clientSecret;

    /**
     * 用户名
     */
    private String username;

    /**
     * 刷新令牌
     */
    private String refreshToken;
}
