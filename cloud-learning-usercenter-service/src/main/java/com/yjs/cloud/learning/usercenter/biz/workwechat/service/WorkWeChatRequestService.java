package com.yjs.cloud.learning.usercenter.biz.workwechat.service;

import com.alibaba.fastjson.JSONObject;
import com.yjs.cloud.learning.usercenter.biz.workwechat.config.WorkWeChatConfig;
import com.yjs.cloud.learning.usercenter.common.util.RemoteServiceUtils;
import com.yjs.cloud.learning.usercenter.common.web.GlobalException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * 企业微信远程调用服务
 *
 * @author Bill.lai
 * @since 2020/5/29
 */
@AllArgsConstructor
@Component
@Slf4j
public class WorkWeChatRequestService {

    private final WorkWeChatConfig workWeChatConfig;

    /**
     * get方法
     * @param url 地址
     * @param params 参数
     * @return 返回对象
     */
    public JSONObject get(String url, Map<String, Object> params) {
        JSONObject response;
        try {
            params.put("access_token", WorkWeChatTokenService.getToken());
            response = RemoteServiceUtils.get(workWeChatConfig.getApiHost() + url, params, new ParameterizedTypeReference<JSONObject>() {});
            if (CollectionUtils.isEmpty(response)) {
                log.error("调用企业微信接口返回结果为空。请求地址={}，参数：{}", workWeChatConfig.getApiHost() + url, params);
                return null;
            }
            String errCodeKey = "errcode";
            if (response.getInteger(errCodeKey) != 0) {
                log.error("调用企业微信接口请求处理失败。请求地址={}，参数：{}，返回结果：{}", workWeChatConfig.getApiHost() + url, params, response);
                throw new GlobalException(response.getString("errmsg"));
            }
        } catch (Exception e) {
            log.error("调用企业微信接口请求处理出错。请求地址={}，参数：{}", workWeChatConfig.getApiHost() + url, params);
            throw new GlobalException("调用企业微信接口请求处理出错，" + e.getMessage());
        }
        return response;
    }

}
