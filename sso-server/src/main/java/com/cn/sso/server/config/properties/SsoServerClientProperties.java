package com.cn.sso.server.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Chen Nan
 */
@Data
@ConfigurationProperties(prefix = "cn.sso.oauth2.clients")
public class SsoServerClientProperties {
    /**
     * 应用ID
     */
    private String clientId;

    /**
     * 应用密钥
     */
    private String clientSecret;

    /**
     * token有效时长，默认0，永不过期
     */
    private int accessTokenValiditySeconds;

    /**
     * refresh_token有效时长，默认0，永不过期
     */
    private int refreshTokenValiditySeconds;
}
