package com.yjs.cloud.learning.gateway.config;

import com.yjs.cloud.learning.gateway.biz.member.MemberApi;
import com.yjs.cloud.learning.gateway.biz.member.UnifiedResponse;
import com.yjs.cloud.learning.gateway.dto.*;
import com.yjs.cloud.learning.gateway.util.CurrentUserUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Router Config
 *
 * @author Andrew.xiao
 * @since 2020/7/29
 */
@Configuration
@AllArgsConstructor
public class RouterFunctionConfig {

    private final RouterUriConfig routerUriConfig;

    private final MemberApi memberApi;

    private final WebClient webClient;

    @Bean
    public RouterFunction<?> routerFunction() {
        return route(POST("/login"), this::login)
                .andRoute(POST("/login/refresh"), this::loginRefresh)
                .andRoute(POST("/login/auth-code"), this::auth)
                .andRoute(POST("/login/auth-code/refresh"), this::refreshAuth)
                .andRoute(POST("/login/admin"), this::adminLogin)
                .andRoute(POST("/login/admin/refresh"), this::refreshAdminLogin)
                .andRoute(POST("/login/admin/auth-code"), this::adminAuth)
                .andRoute(POST("/login/work-we-chat"), this::workWeChatAuth)
                .andRoute(POST("/login/ding-talk"), this::dingTalkAuth)
                .andRoute(POST("/login/service"), this::serviceAuth)
                .andRoute(POST("/login/service/refresh"), this::refreshAuthService)
                .andRoute(GET("/current-member"), this::currentMember)
                .andRoute(GET("/current-user"), this::currentUser);
    }

    /**
     * get current member info
     * @param request auth2Authentication
     * @return Mono<ServerResponse>
     */
    private Mono<ServerResponse> currentUser(ServerRequest request) {
        UserDTO userDTO = CurrentUserUtils.get(request);
        if (userDTO == null) {
            return ServerResponse.ok().body(Mono.error(new UnsupportedOperationException()), Void.class);
        }else{
            Mono<UserDTO> mono = Mono.justOrEmpty(userDTO);
            Mono<Map> mapMono = mono.flatMap(m -> {
                Map<String, Object> res = new HashMap<>(16);
                res.put("code", 0);
                res.put("msg", "ok");
                res.put("data", m);
                return Mono.just(res);
            });
            return ServerResponse
                    .ok()
                    .body(mapMono, HashMap.class);
        }
    }

    /**
     * get current member info
     * @param request auth2Authentication
     * @return Mono<ServerResponse>
     */
    private Mono<ServerResponse> currentMember(ServerRequest request) {
        UserDTO userDTO = CurrentUserUtils.get(request);
        if (userDTO == null) {
            return ServerResponse
                    .ok()
                    .body(Mono.error(new UnsupportedOperationException()), Void.class);
        }else{
            Mono<UnifiedResponse> memberMono = memberApi.getMemberByMobile(userDTO.getUsername());
            return ServerResponse
                    .ok()
                    .body(memberMono, UnifiedResponse.class);
        }
    }

    /**
     * authentication
     * @param request ServerRequest
     * @return Mono<ServerResponse>
     */
    private Mono<ServerResponse> login(ServerRequest request) {
        Mono<PasswordAuthenticationRequest> authenticationRequestMono = request.bodyToMono(PasswordAuthenticationRequest.class);
        Mono<String> oauth2RequestMono = authenticationRequestMono.map(n -> "username=" + n.getUsername() +
                "&password=" + n.getPassword() +
                "&grant_type=password&scope=web-client-password"
        );
        return authResponseMono(oauth2RequestMono);
    }

    /**
     * refresh auth
     * @param request ServerRequest
     * @return Mono<ServerResponse>
     */
    private Mono<ServerResponse> loginRefresh(ServerRequest request) {
        Mono<AuthenticationRequest> authenticationRequestMono = request.bodyToMono(AuthenticationRequest.class);
        Mono<String> oauth2RequestMono = authenticationRequestMono.map(n -> "refresh_token=" +n.getRefreshToken() +
                "&grant_type=refresh_token&scope=web-client-password"
        );
        return authResponseMono(oauth2RequestMono);
    }

    /**
     * authentication
     * @param request ServerRequest
     * @return Mono<ServerResponse>
     */
    private Mono<ServerResponse> auth(ServerRequest request) {
        Mono<AuthenticationRequest> authenticationRequestMono = request.bodyToMono(AuthenticationRequest.class);
        Mono<String> oauth2RequestMono = authenticationRequestMono.map(n -> "username=" +n.getMobile() +
                    "&password=" +n.getAuthCode() +
                    "&grant_type=password&scope=web-client"
        );
        return authResponseMono(oauth2RequestMono);
    }

    /**
     * refresh auth
     * @param request ServerRequest
     * @return Mono<ServerResponse>
     */
    private Mono<ServerResponse> refreshAuth(ServerRequest request) {
        Mono<AuthenticationRequest> authenticationRequestMono = request.bodyToMono(AuthenticationRequest.class);
        Mono<String> oauth2RequestMono = authenticationRequestMono.map(n -> "refresh_token=" +n.getRefreshToken() +
                "&grant_type=refresh_token&scope=web-client"
        );
        return authResponseMono(oauth2RequestMono);
    }

    /**
     * authentication
     * @param request ServerRequest
     * @return Mono<ServerResponse>
     */
    private Mono<ServerResponse> adminLogin(ServerRequest request) {
        Mono<PasswordAuthenticationRequest> authenticationRequestMono = request.bodyToMono(PasswordAuthenticationRequest.class);
        Mono<String> oauth2RequestMono = authenticationRequestMono.map(n -> "username=" + n.getUsername() +
                "&password=" + n.getPassword() +
                "&grant_type=password&scope=admin-client-password"
        );
        return authResponseMono(oauth2RequestMono);
    }

    /**
     * refresh auth
     * @param request ServerRequest
     * @return Mono<ServerResponse>
     */
    private Mono<ServerResponse> refreshAdminLogin(ServerRequest request) {
        Mono<AuthenticationRequest> authenticationRequestMono = request.bodyToMono(AuthenticationRequest.class);
        Mono<String> oauth2RequestMono = authenticationRequestMono.map(n -> "refresh_token=" +n.getRefreshToken() +
                "&grant_type=refresh_token&scope=admin-client-password"
        );
        return authResponseMono(oauth2RequestMono);
    }

    private Mono<ServerResponse> adminAuth(ServerRequest request) {
        Mono<AuthenticationRequest> authenticationRequestMono = request.bodyToMono(AuthenticationRequest.class);
        Mono<String> oauth2RequestMono = authenticationRequestMono.map(n -> "username=" +n.getMobile() +
                "&password=" +n.getAuthCode() +
                "&grant_type=password&scope=admin-client"
        );
        return authResponseMono(oauth2RequestMono);
    }

    private Mono<ServerResponse> workWeChatAuth(ServerRequest request) {
        Mono<WorkWeChatAuthenticationRequest> authenticationRequestMono = request.bodyToMono(WorkWeChatAuthenticationRequest.class);
        Mono<String> oauth2RequestMono = authenticationRequestMono.map(n -> "username=" + n.getCode() +
                "&password=" + n.getCode() + "&state=" + n.getState() +
                "&grant_type=password&scope=work-we-chat-client"
        );
        return authResponseMono(oauth2RequestMono);
    }

    private Mono<ServerResponse> dingTalkAuth(ServerRequest request) {
        Mono<DingTalkAuthenticationRequest> authenticationRequestMono = request.bodyToMono(DingTalkAuthenticationRequest.class);
        Mono<String> oauth2RequestMono = authenticationRequestMono.map(n -> "username=" + n.getCode() +
                "&password=" + n.getCode() +
                "&grant_type=password&scope=ding-talk-client"
        );
        return authResponseMono(oauth2RequestMono);
    }

    /**
     * authentication
     * @param request ServerRequest
     * @return Mono<ServerResponse>
     */
    private Mono<ServerResponse> serviceAuth(ServerRequest request) {
        Mono<PasswordAuthenticationRequest> authenticationRequestMono = request.bodyToMono(PasswordAuthenticationRequest.class);
        Mono<String> oauth2RequestMono = authenticationRequestMono.map(n -> "username=" + n.getUsername() +
                "&password=" + n.getPassword() +
                "&grant_type=password&scope=service-client"
        );
        return authResponseMono(oauth2RequestMono);
    }

    /**
     * refresh auth
     * @param request ServerRequest
     * @return Mono<ServerResponse>
     */
    private Mono<ServerResponse> refreshAuthService(ServerRequest request) {
        Mono<PasswordAuthenticationRequest> authenticationRequestMono = request.bodyToMono(PasswordAuthenticationRequest.class);
        Mono<String> oauth2RequestMono = authenticationRequestMono.map(n -> "refresh_token=" +n.getRefreshToken() +
                "&grant_type=refresh_token&scope=service-client"
        );
        return authResponseMono(oauth2RequestMono);
    }

    private Mono<ServerResponse> authResponseMono(Mono<String> oauth2RequestMono) {
        Mono<ClientResponse> responseMono = webClient.post()
                .uri(URI.create(routerUriConfig.getAuthService() + "/oauth/token"))
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .headers(headers -> headers.setBasicAuth("web", "web@secret"))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(oauth2RequestMono, String.class)
                .exchange();

        return responseMono.flatMap(cr -> {
            Mono<Map> mapMono = cr.bodyToMono(HashMap.class);
            if (cr.rawStatusCode() != HttpStatus.OK.value()) {
                return ServerResponse.status(cr.rawStatusCode()).body(mapMono, HashMap.class);
            }
            mapMono = cr.bodyToMono(Map.class).flatMap(m -> {
                if (m.get("code") == null) {
                    Map<String, Object> res = new HashMap<>(16);
                    res.put("code", 0);
                    res.put("msg", "ok");
                    res.put("data", m);
                    m = res;
                }
                return Mono.just(m);
            });
            return ServerResponse.ok().body(mapMono, HashMap.class);
        });
    }
}
