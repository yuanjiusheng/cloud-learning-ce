package com.yjs.cloud.learning.gateway.config;

import com.yjs.cloud.learning.gateway.web.PermitAllUrlFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 资源服务器配置
 *
 * @author Andrew.xiao
 * @since 2020/7/17
 */
@AllArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {

    private final PermitAllUrlFilter permitAllUrlFilter;
    private final PermitAllUrlConfig permitAllUrlConfig;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
        http.addFilterBefore(permitAllUrlFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        // 无需验证API，去除token
        if (permitAllUrlConfig != null && permitAllUrlConfig.getPatterns() != null) {
            for (String pattern : permitAllUrlConfig.getPatterns()) {
                http.authorizeExchange().pathMatchers(pattern).permitAll();
            }
        }
        http.authorizeExchange()
                //白名单配置
                .pathMatchers("/auth/oauth/token").permitAll()
                .pathMatchers("/auth/auth-code").permitAll()
                .pathMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.jpg",
                        "/**/*.png",
                        "/**/*.jpeg"
                ).permitAll()
                .pathMatchers(
                        HttpMethod.GET,
                        "/**/*swagger*/**",
                        "/**/*.ico",
                        "/*/v2/api-docs",
                        "/**/csrf"
                ).permitAll()
                .pathMatchers("/swagger-resources/**").permitAll()
                .pathMatchers("/webjars/**").permitAll()
                .pathMatchers("/demo/**").permitAll()
                // public-api为公共API，无需登录也可访问
                .pathMatchers("/**/public-api/**").permitAll()
                // auth-api为需要验证API，只需登录即可访问
                .pathMatchers("/**/auth-api/**").authenticated()
                // oss服务
                .pathMatchers("/oss/**").authenticated()
                // 系统设置
                .pathMatchers("/setting/carousel/**").hasAnyAuthority("auth_setting_carousel")
                // 获取当前信息
                .pathMatchers("/current-member").permitAll()
                .pathMatchers("/current-user").permitAll()
                .pathMatchers("/auth/user/current").permitAll()
                .pathMatchers("/auth/work-wechat/config").permitAll()
                // 会员服务
                .pathMatchers("/member/**").hasAnyAuthority("auth_member_list")
                .pathMatchers("/member/level/**").hasAnyAuthority("auth_member_level", "auth_member_list")
                // 直播管理
                .pathMatchers("/live/channel/**").hasAnyAuthority("auth_live_channel")
                .pathMatchers("/live/category/**").hasAnyAuthority("auth_live_category", "auth_live_channel")
                // 课程管理
                .pathMatchers("/learning/**").hasAnyAuthority("auth_learning_list")
                .pathMatchers("/learning/category/**").hasAnyAuthority("auth_learning_category")
                // 测评管理
                .pathMatchers("/exam/question-lib/**").hasAnyAuthority("auth_exam_question_lib")
                .pathMatchers("/exam/page/**").hasAnyAuthority("auth_exam_page_list")
                .pathMatchers("/exam/mark/**").hasAnyAuthority("auth_exam_mark")
                .pathMatchers("/exam/mock/**").hasAnyAuthority("auth_exam_mock")
                .pathMatchers("/exam/category/**").hasAnyAuthority("auth_exam_category")
                .pathMatchers("/exam/**").hasAnyAuthority("auth_exam_list")
                // 组织架构
                .pathMatchers("/organizational/user/**").hasAnyAuthority("auth_shop", "auth_organizational_user")
                // 权限认证
                .pathMatchers("/authority/authority/**").hasAnyAuthority("auth_authority_authority")
                .pathMatchers("/authority/role/**", "/order/update/setting/**").hasAnyAuthority("auth_authority_role")
                // 系统设置
                .pathMatchers("/setting/carousel/**").hasAnyAuthority("auth_setting_carousel")
                .pathMatchers("/setting/agreement/**").hasAnyAuthority("auth_setting_agreement")
                // 其他登录才能访问
                .and().authorizeExchange().anyExchange().authenticated()
                .and().exceptionHandling()
                .and().csrf().disable();
        return http.build();
    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("auth_");
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}
