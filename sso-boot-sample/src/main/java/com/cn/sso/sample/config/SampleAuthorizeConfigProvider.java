package com.cn.sso.sample.config;

import com.cn.sso.client.authorize.AuthorizeConfigProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author Chen Nan
 */
@Slf4j
@Component
@Order
public class SampleAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.anyRequest().access("@rbacServiceImpl.hasPermission(request,authentication)");
        return true;
    }
}
