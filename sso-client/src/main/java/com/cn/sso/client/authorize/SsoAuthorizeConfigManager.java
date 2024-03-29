package com.cn.sso.client.authorize;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 默认的授权配置管理器
 *
 * @author Chen Nan
 */
@Component
@Slf4j
public class SsoAuthorizeConfigManager implements AuthorizeConfigManager {

    @Autowired(required = false)
    private List<AuthorizeConfigProvider> authorizeConfigProviders;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        boolean existAnyRequestConfig = false;
        String existAnyRequestConfigName = null;

        if (authorizeConfigProviders != null) {
            for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders) {
                log.info("【授权配置】" + authorizeConfigProvider);
                boolean currentIsAnyRequestConfig = authorizeConfigProvider.config(config);
                if (existAnyRequestConfig && currentIsAnyRequestConfig) {
                    throw new RuntimeException("重复的anyRequest配置:" + existAnyRequestConfigName + ","
                            + authorizeConfigProvider.getClass().getSimpleName());
                } else if (currentIsAnyRequestConfig) {
                    existAnyRequestConfig = true;
                    existAnyRequestConfigName = authorizeConfigProvider.getClass().getSimpleName();
                }
            }
        }

        if (!existAnyRequestConfig) {
            config.anyRequest().authenticated();
        }
    }
}
