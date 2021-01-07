package com.yjs.cloud.learning.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 无须登录认证和授权即可访问的URL Pattern Config
 *
 * @author Andrew.xiao
 * @since 2020/9/28
 */
@Component
@ConfigurationProperties(prefix = "permit-all-url")
@Data
public class PermitAllUrlConfig {

    private List<String> patterns;

}
