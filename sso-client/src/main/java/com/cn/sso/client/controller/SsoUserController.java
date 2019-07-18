package com.cn.sso.client.controller;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.http.HttpRequest;
import com.cn.sso.client.config.properties.SsoClientProperties;
import com.cn.sso.client.model.SsoUserLoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chen Nan
 */
@RestController
@RequestMapping("/sso/user")
@Slf4j
public class SsoUserController {

    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;
    @Autowired
    private SsoClientProperties oAuth2Properties;

    @PostMapping("/login")
    public String login(@RequestBody SsoUserLoginDTO req) {
        log.info("【登录】req={}", req);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("grant_type", "password");
        paramMap.put("username", req.getUsername());
        paramMap.put("password", req.getPassword());
        String serverUri = oAuth2Properties.getServerUri();
        if (StringUtils.isBlank(serverUri)) {
            throw new RuntimeException("未配置认证中心地址");
        }

        String clientInfo = Base64Encoder.encode(oAuth2ClientProperties.getClientId() + ":" + oAuth2ClientProperties.getClientSecret());
        return HttpRequest.post(serverUri)
                .header("Authorization", "Basic " + clientInfo)
                .form(paramMap)
                .execute()
                .body();
    }

    @PostMapping("/refresh_token")
    public String refreshToken(@RequestHeader String refresh_token) {
        refresh_token = refresh_token.replaceFirst("bearer ", "");
        log.info("【刷新Token】refreshToken={}", refresh_token);


        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("grant_type", "refresh_token");
        paramMap.put("refresh_token", refresh_token);
        String serverUri = oAuth2Properties.getServerUri();
        if (StringUtils.isBlank(serverUri)) {
            throw new RuntimeException("未配置认证中心地址");
        }

        String clientInfo = Base64Encoder.encode(oAuth2ClientProperties.getClientId() + ":" + oAuth2ClientProperties.getClientSecret());


        return HttpRequest.post(serverUri)
                .header("Authorization", "Basic " + clientInfo)
                .form(paramMap)
                .execute()
                .body();
    }
}
