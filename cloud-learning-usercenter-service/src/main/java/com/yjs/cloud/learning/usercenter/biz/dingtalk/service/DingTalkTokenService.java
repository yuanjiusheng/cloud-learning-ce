package com.yjs.cloud.learning.usercenter.biz.dingtalk.service;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.taobao.api.ApiException;
import com.yjs.cloud.learning.usercenter.biz.dingtalk.config.DingTalkConfig;
import com.yjs.cloud.learning.usercenter.common.web.GlobalException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 钉钉Token服务
 * @author Bill.lai
 * @since 2019-08-21
 */
@Slf4j
@Configuration
@EnableScheduling
@AllArgsConstructor
public class DingTalkTokenService {

    @Getter
    private static String token = "";
    private final DingTalkConfig dingTalkConfig;

    /**
     * 定时刷新钉钉Token
     * 6600秒执行一次
     */
    @Scheduled(fixedRate = 6600000)
    public void getAccessToken(){
        OapiGettokenResponse response;
        try {
            DefaultDingTalkClient client = new DefaultDingTalkClient(dingTalkConfig.getApiHost() + "/gettoken");
            OapiGettokenRequest request = new OapiGettokenRequest();
            request.setAppkey(dingTalkConfig.getAppKey());
            request.setAppsecret(dingTalkConfig.getAppSecret());
            request.setHttpMethod("GET");
            response = client.execute(request);
            if (response != null) {
                token = response.getAccessToken();
            }
        } catch (ApiException e) {
            log.error("获取企业微信token出错", e);
            throw new GlobalException(e.getMessage());
        }
    }

}

