package com.cn.sso.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Chen Nan
 */
@SpringBootApplication
@ComponentScan({
        "com.cn.sso.sample",
        "com.cn.sso.core",
        "com.cn.sso.server",
        "com.cn.sso.client"
})
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }
}
