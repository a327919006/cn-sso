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
        "com.cn.sso.server"
})
public class SampleServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleServerApplication.class, args);
    }
}
