package com.yjs.cloud.learning.usercenter.biz.dingtalk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 钉钉配置
 *
 * @author Bill.lai
 * @since 2020/5/29
 */
@Data
@Component
@ConfigurationProperties(prefix = "oauth2.ding-talk")
public class DingTalkConfig {

    private String apiHost;

    private String corpId;

    private String appKey;

    private String appSecret;

    private String token;

    private String aesKey;

    private String agentId;

    private String loginAppId;

    private String loginAppSecret;

}
