package com.cn.sso.sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chen Nan
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @GetMapping("/me")
    public Authentication me(Authentication user) {
        log.info("获取个人信息");
        return user;
    }
}
