package com.yjs.cloud.learning.usercenter.biz.workwechat.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 企业微信配置
 *
 * @author Bill.lai
 * @since 2020/5/29
 */
@Data
@Component
@ConfigurationProperties(prefix = "oauth2.work-we-chat")
public class WorkWeChatConfig {

    private String apiHost;

    private String corpId;

    private String corpSecret;

    private String state;

    private String agentId;

}
