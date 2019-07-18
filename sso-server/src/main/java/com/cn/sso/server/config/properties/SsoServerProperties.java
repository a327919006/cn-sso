package com.cn.sso.server.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Chen Nan
 */
@Data
@Component
@ConfigurationProperties(prefix = "cn.sso")
public class SsoServerProperties {
    /**
     * oauth2配置
     */
    private SsoServerOauth2Properties oauth2 = new SsoServerOauth2Properties();
}
