package com.cn.sso.server.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Chen Nan
 */
@Data
@ConfigurationProperties(prefix = "cn.sso.oauth2")
public class SsoServerOauth2Properties {
    /**
     * JWT签名密钥
     */
    private String jwtSigningKey = "sample";

    /**
     * 应用配置
     */
    private SsoServerClientProperties[] clients = {};
}
