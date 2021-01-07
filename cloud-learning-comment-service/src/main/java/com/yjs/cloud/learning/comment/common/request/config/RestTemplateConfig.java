package com.yjs.cloud.learning.comment.common.request.config;

import com.yjs.cloud.learning.comment.common.loginuser.LoginUserHolder;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * RestTemplate 配置
 *
 * @author Andrew.xiao
 * @since 2020/7/19
 */
@AllArgsConstructor
@Configuration
public class RestTemplateConfig {

    private final LoginUserHolder loginUserHolder;

    @Primary
    @Bean
    public RestTemplate getCustomRestTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        //30s超时
        requestFactory.setConnectTimeout(30*1000);
        requestFactory.setReadTimeout(30*1000);
        RestTemplate template = new RestTemplate(requestFactory);
        List<ClientHttpRequestInterceptor> interceptors = template.getInterceptors();
        ClientHttpRequestInterceptor clientHttpRequestInterceptor = ((HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution) ->{
            HttpHeaders headers = httpRequest.getHeaders();
            headers.add("Authorization", loginUserHolder.getCurrentUser().getBearerToken());
            return clientHttpRequestExecution.execute(httpRequest, body);
        });

        if (CollectionUtils.isEmpty(interceptors)) {
            template.setInterceptors(Collections.singletonList(clientHttpRequestInterceptor));
        } else {
            interceptors.add(clientHttpRequestInterceptor);
            template.setInterceptors(interceptors);
        }
        return template;
    }

}
