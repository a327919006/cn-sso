package com.cn.sso.client.config;

import com.cn.sso.client.authorize.AuthorizeConfigManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

/**
 * @author Chen Nan
 */
@Configuration("clientResourceServerConfig")
@EnableResourceServer
public class SsoResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private OAuth2WebSecurityExpressionHandler expressionHandler;
    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.expressionHandler(expressionHandler);
    }
}
