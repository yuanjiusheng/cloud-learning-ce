package com.yjs.cloud.learning.member.common.request.service;

import com.alibaba.fastjson.JSONObject;
import com.yjs.cloud.learning.member.common.util.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 远程服务连接工具
 * @author Bill.lai
 * @since 2019-07-11
 */
@AllArgsConstructor
@Slf4j
@Component
public class RemoteRequestService {

    private final RestTemplate restTemplate;

    /**
     * post请求方法
     * @param server 服务器地址
     * @param requestParams 请求参数
     * @param responseType 返回类型
     * @param <T> 泛型
     * @return 请求结果
     */
    public <T> T post(String server, Object requestParams, ParameterizedTypeReference<T> responseType){
        return method(server, requestParams, responseType, HttpMethod.POST);
    }

    /**
     * put请求方法
     * @param server 服务器地址
     * @param requestParams 请求参数
     * @param responseType 返回类型
     * @param <T> 泛型
     * @return 请求结果
     */
    public <T> T put(String server, Object requestParams, ParameterizedTypeReference<T> responseType){
        return method(server, requestParams, responseType, HttpMethod.PUT);
    }

    /**
     * put请求方法
     * @param server 服务器地址
     * @param requestParams 请求参数
     * @param responseType 返回类型
     * @param <T> 泛型
     * @return 请求结果
     */
    public <T> T delete(String server, Object requestParams, ParameterizedTypeReference<T> responseType){
        return method(server, requestParams, responseType, HttpMethod.DELETE);
    }

    /**
     * post请求方法
     * @param server 服务器地址
     * @param requestParams 请求参数
     * @param responseType 返回类型
     * @param <T> 泛型
     * @return 请求结果
     */
    private <T> T method(String server, Object requestParams, ParameterizedTypeReference<T> responseType, HttpMethod httpMethod){
        String paramJson = null;
        if(requestParams != null){
            paramJson = JsonUtils.toJsonString(requestParams);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(paramJson, headers);
        //发送请求
        ResponseEntity<T> responseEntity = restTemplate.exchange(server, httpMethod, entity, responseType);
        //获取返回数据
        return responseEntity.getBody();
    }

    /**
     * post请求方法
     * @param server 服务器地址
     * @param requestParams 请求参数
     * @param responseType 返回类型
     * @param <T> 泛型
     * @return 请求结果
     */
    public <T> T postForm(String server, Object requestParams, ParameterizedTypeReference<T> responseType){
        return formMethod(server, requestParams, responseType);
    }

    /**
     * post请求方法
     * @param <T> 泛型
     * @param server 服务器地址
     * @param requestParams 请求参数
     * @param responseType 返回类型
     * @return 请求结果
     */
    private <T> T formMethod(String server, Object requestParams, ParameterizedTypeReference<T> responseType){
        Map<String, Object> paramJson = null;
        if(requestParams != null){
            paramJson = JSONObject.parseObject(JsonUtils.toJsonString(requestParams));
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();

        if(!CollectionUtils.isEmpty(paramJson)){
            for (String key : paramJson.keySet()) {
                map.add(key, paramJson.get(key) != null ? paramJson.get(key).toString() : "null");
            }
        }

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        //发送请求
        ResponseEntity<T> responseEntity = restTemplate.exchange(server, HttpMethod.POST, entity, responseType);
        //获取返回数据
        return responseEntity.getBody();
    }

    /**
     * get请求方法
     * @param <T> 泛型
     * @param server 服务器地址
     * @param responseType 返回类型
     * @return 请求结果
     */
    public <T> T get(String server, Map<String, Object> params, ParameterizedTypeReference<T> responseType){
        StringBuilder sb = new StringBuilder();
        if(!CollectionUtils.isEmpty(params)){
            sb.append("?");
            int count = 1;
            for(String key : params.keySet()) {
                Object value = params.get(key);
                if(value instanceof List) {
                    List<String> stringList = new ArrayList<>();
                    for (Object s : (List<?>) value) {
                        stringList.add(s.toString());
                    }
                    value = String.join(",", stringList);
                }
                sb.append(key).append("=");
                if(value != null){
                    sb.append(value);
                }
                if(count < params.size()){
                    sb.append("&");
                }
                count++;
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Object> requestEntity = new HttpEntity<>(new HashMap<>(1), headers);
        //发送请求
        ResponseEntity<T> responseEntity = restTemplate.exchange(server + sb.toString(), HttpMethod.GET, requestEntity, responseType);
        //获取返回数据
        return responseEntity.getBody();
    }

}
