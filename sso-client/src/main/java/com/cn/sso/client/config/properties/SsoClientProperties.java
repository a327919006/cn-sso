package com.cn.sso.client.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Chen Nan
 */
@Data
@Component
@ConfigurationProperties(prefix = "cn.sso.oauth2")
public class SsoClientProperties {
    /**
     * 认证中心地址
     */
    private String serverUri;

}
