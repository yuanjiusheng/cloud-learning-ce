package com.yjs.cloud.learning.usercenter.biz.workwechat.service;

import com.alibaba.fastjson.JSONObject;
import com.yjs.cloud.learning.usercenter.biz.workwechat.config.WorkWeChatConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 企业微信Token服务类
 * @author Bill.lai
 * @since 2019-08-21
 */
@Configuration
@EnableScheduling
@AllArgsConstructor
@Slf4j
public class WorkWeChatTokenService {

    @Getter
    private static String token = "";
    private final WorkWeChatConfig workWeChatConfig;
    private final WorkWeChatRequestService workWeChatRequestService;

    /**
     * 定时刷新Token
     * 7080秒执行一次
     */
    @Scheduled(fixedRate = 7080000)
    public JSONObject getAccessToken(){
        JSONObject response = null;
        try {
            Map<String, Object> params = new HashMap<>(2);
            params.put("corpid", workWeChatConfig.getCorpId());
            params.put("corpsecret", workWeChatConfig.getCorpSecret());
            response = workWeChatRequestService.get("/gettoken", params);
            if (!CollectionUtils.isEmpty(response)) {
                token = response.getString("access_token");
            }
        } catch (Exception e) {
            log.error("获取企业微信token出错，获取结果：{}", response, e);
            throw e;
        }
        return response;
    }

}

