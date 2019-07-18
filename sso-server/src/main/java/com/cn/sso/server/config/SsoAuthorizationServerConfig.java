package com.cn.sso.server.config;

import com.cn.sso.server.config.properties.SsoServerClientProperties;
import com.cn.sso.server.config.properties.SsoServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author Chen Nan
 */
@Configuration
@EnableAuthorizationServer
@Slf4j
public class SsoAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SsoServerProperties securityProperties;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .accessTokenConverter(jwtAccessTokenConverter);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .passwordEncoder(passwordEncoder)
                .tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        if (ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())) {
            for (SsoServerClientProperties client : securityProperties.getOauth2().getClients()) {
                log.info("【授权应用】clientId={}", client.getClientId());
                builder.withClient(client.getClientId())
                        .secret(passwordEncoder.encode(client.getClientSecret()))
                        .accessTokenValiditySeconds(client.getAccessTokenValiditySeconds())
                        .refreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds())
                        .authorizedGrantTypes("password", "refresh_token")
                        .scopes("all")
                        .autoApprove(true);
            }
        }
    }

}
