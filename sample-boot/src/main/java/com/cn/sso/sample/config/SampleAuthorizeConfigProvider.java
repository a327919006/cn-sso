package com.cn.sso.sample.config;

import com.cn.sso.client.authorize.AuthorizeConfigProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 自定义授权配置（可配置多个）
 *
 * @author Chen Nan
 */
@Slf4j
@Component
@Order
public class SampleAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        /**
         * 示例一：指定某些url无需登录即可访问，如注册、忘记密码等
         */
        /*config.antMatchers(
                "/user/register"
        ).permitAll();*/

        /**
         * 示例二：调用指定服务，判断是否有权限
         */
        config.anyRequest().access("@rbacServiceImpl.hasPermission(request,authentication)");
        return true;
    }
}
