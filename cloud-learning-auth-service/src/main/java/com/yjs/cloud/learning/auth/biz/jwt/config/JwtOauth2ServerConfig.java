package com.yjs.cloud.learning.auth.biz.jwt.config;

import com.yjs.cloud.learning.auth.biz.jwt.constant.Oauth2Constant;
import com.yjs.cloud.learning.auth.biz.jwt.service.JwtUserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

/**
 * Oauth2 JWT 配置
 *
 * @author Andrew.xiao
 * @since 2020/7/16
 */
@AllArgsConstructor
@Configuration
@EnableAuthorizationServer
public class JwtOauth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    private final JwtUserDetailsServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenEnhancer jwtTokenEnhancer;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("web")
                .secret(passwordEncoder.encode("web@secret"))
                .scopes(Oauth2Constant.SCOPE_WEB_CLIENT, Oauth2Constant.SCOPE_ADMIN_CLIENT_PASSWORD, Oauth2Constant.SCOPE_ADMIN_CLIENT,
                        Oauth2Constant.SCOPE_SERVICE_CLIENT, Oauth2Constant.SCOPE_WEB_CLIENT_PASSWORD, Oauth2Constant.SCOPE_SSO_CLIENT,
                        Oauth2Constant.SCOPE_DING_TALK_CLIENT, Oauth2Constant.SCOPE_WORK_WE_CHAT_CLIENT)
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(315360000)
                .refreshTokenValiditySeconds(315360000);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(accessTokenConverter());
        enhancerChain.setTokenEnhancers(delegates);
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .accessTokenConverter(accessTokenConverter())
                .tokenEnhancer(enhancerChain);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients();
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    @Bean
    public KeyPair keyPair() {
        // keytool -genkey -alias jwt -keyalg RSA -keystore jwt.jks
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "keystorepass".toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwt", "keypairpass".toCharArray());
    }

}
