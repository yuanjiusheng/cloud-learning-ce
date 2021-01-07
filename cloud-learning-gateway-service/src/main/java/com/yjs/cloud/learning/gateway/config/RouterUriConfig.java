package com.yjs.cloud.learning.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 路由 uri 配置
 *
 * @author Andrew.xiao
 * @since 2020/7/15
 */
@Component
@ConfigurationProperties(prefix = "router.uri")
@Data
public class RouterUriConfig {

    /**
     * 认证服务地址
     */
    private String authService;

    /**
     * 会员服务地址
     */
    private String memberService;

    /**
     * 用户中心服务地址
     */
    private String userService;

}
