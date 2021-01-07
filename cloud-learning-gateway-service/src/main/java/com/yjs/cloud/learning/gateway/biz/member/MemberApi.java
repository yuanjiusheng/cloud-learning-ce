package com.yjs.cloud.learning.gateway.biz.member;

import com.yjs.cloud.learning.gateway.config.RouterUriConfig;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * 会员服务代理
 *
 * @author Andrew.xiao
 * @since 2020/7/22
 */
@Service
@AllArgsConstructor
public class MemberApi {

    private final WebClient webClient;
    private final RouterUriConfig routerUriConfig;

    /**
     * 根据手机号码获取会员
     * @param mobile 手机号码
     * @return 会员信息
     */
    public Mono<UnifiedResponse> getMemberByMobile(String mobile){
        Mono<ClientResponse> response = webClient
                .get()
                .uri(routerUriConfig.getMemberService() + "/auth-api/by-mobile?mobile={mobile}", mobile)
                .exchange();
        return response.flatMap(resp -> {
            if (resp.statusCode() == HttpStatus.OK) {
                return resp.bodyToMono(UnifiedResponse.class);
            }
            return Mono.error(new RuntimeException("Unknown" + resp.statusCode()));
        });
    }
}

