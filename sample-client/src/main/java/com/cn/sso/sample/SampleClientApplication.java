package com.cn.sso.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Chen Nan
 */
@SpringBootApplication
@ComponentScan({
        "com.cn.sso.sample",
        "com.cn.sso.core",
        "com.cn.sso.client"
})
public class SampleClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleClientApplication.class, args);
    }
}
