package com.yjs.cloud.learning.auth.common.request.service;

import com.alibaba.fastjson.JSONObject;
import com.yjs.cloud.learning.auth.common.config.GatewayConfig;
import com.yjs.cloud.learning.auth.common.response.UnifiedResponse;
import com.yjs.cloud.learning.auth.common.web.GlobalException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 远程服务连接工具
 * @author Bill.lai
 * @since 2019-07-11
 */
@AllArgsConstructor
@Slf4j
@Component
public class RequestService {

    private final RemoteRequestService requestService;
    private final GatewayConfig gatewayConfig;

    public <T> T get(String path, Map<String, Object> param, Class<T> clazz) {
        String url = gatewayConfig.getUrl() + path;
        T t = null;
        try {
            UnifiedResponse unifiedResponse = requestService.get(url, param, new ParameterizedTypeReference<UnifiedResponse>() {});
            if (unifiedResponse.getCode() != 0) {
                throw new GlobalException(unifiedResponse.getMsg());
            }
            if (unifiedResponse.getData() == null) {
                return null;
            }
            t = JSONObject.parseObject(JSONObject.toJSONString(unifiedResponse.getData()), clazz);
        } catch (Exception e) {
            log.error("request error，url: {}, param：{}", path, param, e);
        }
        return t;
    }

}
