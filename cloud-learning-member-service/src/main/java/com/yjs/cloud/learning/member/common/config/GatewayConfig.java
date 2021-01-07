package com.yjs.cloud.learning.member.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 网关配置
 *
 * @author Bill.lai
 * @since 2020/7/24
 */
@Data
@ConfigurationProperties(prefix="gateway.service")
@Configuration
public class GatewayConfig {

    private String url;

}
