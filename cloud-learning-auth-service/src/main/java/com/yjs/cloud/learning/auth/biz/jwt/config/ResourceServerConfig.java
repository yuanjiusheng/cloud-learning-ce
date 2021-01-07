package com.yjs.cloud.learning.auth.biz.jwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源访问配置
 *
 * @author Andrew.xiao
 * @since 2020/7/18
 */
@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();
//        http.authorizeRequests()
//                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
//                .antMatchers("/jwt/publicKey").permitAll()
//                .antMatchers("/auth-code").permitAll()
//                .antMatchers("/v2/api-docs").permitAll()
//                .anyRequest().authenticated();
    }

}
