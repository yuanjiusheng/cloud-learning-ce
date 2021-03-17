package com.yjs.cloud.learning.gateway.web;

import com.yjs.cloud.learning.gateway.config.PermitAllUrlConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import java.net.URI;
import java.util.List;

/**
 * 无须登录认证和授权即可访问的URL，自动去掉Authorization Header
 *
 * @author Andrew.xiao
 * @since 2020/9/28
 */
@Slf4j
@Component
public class PermitAllUrlFilter implements WebFilter {

    private PermitAllUrlConfig permitAllUrlConfig;

    private final PathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    public void setPermitAllUrlConfig(PermitAllUrlConfig permitAllUrlConfig) {
        this.permitAllUrlConfig = permitAllUrlConfig;
    }

    @Override
    public Mono<Void> filter(@Nullable ServerWebExchange exchange, @Nullable WebFilterChain chain) {
        if (exchange == null) {
            return null;
        }
        if (chain == null) {
            return null;
        }
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        List<String> urlPatterns = permitAllUrlConfig.getPatterns();
        for (String urlPattern : urlPatterns) {
            boolean match = pathMatcher.match("/**/public-api/**", uri.getPath()) && !"/comment/public-api/comment/list".equals(uri.getPath());
            if (pathMatcher.match(urlPattern, uri.getPath())) {
                if (HttpMethod.GET.equals(exchange.getRequest().getMethod())) {
                    request = exchange.getRequest().mutate().header("Authorization", "").build();
                    return chain.filter(exchange.mutate().request(request).build());
                } else if (HttpMethod.POST.equals(exchange.getRequest().getMethod())) {
                    if (urlPattern.startsWith("/login")) {
                        request = exchange.getRequest().mutate().header("Authorization", "").build();
                        return chain.filter(exchange.mutate().request(request).build());
                    }
                }
            }
        }
        return chain.filter(exchange);
    }
}

