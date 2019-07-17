package com.cn.sso.core.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Chen Nan
 */
@Data
@Component
@ConfigurationProperties(prefix = "boot.sample.security")
public class SecurityProperties {
    /**
     * oauth2配置
     */
    private OAuth2Properties oauth2 = new OAuth2Properties();
}
