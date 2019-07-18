package com.cn.sso.sample.service.impl;

import cn.hutool.json.JSONUtil;
import com.cn.sso.sample.service.RbacService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Chen Nan
 */
@Component
@Slf4j
public class RbacServiceImpl implements RbacService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        log.info("【hasPermission】");
        Object principal = authentication.getPrincipal();
        log.info("authentication={}", JSONUtil.toJsonStr(authentication));
        log.info("principal={}", JSONUtil.toJsonStr(principal));

        boolean hasPermission = false;

        String adminUser = "admin";
        String uri = request.getRequestURI();
        if (principal instanceof String) {
            if (principal.equals(adminUser)) {
                hasPermission = true;
            }
        }
        if (!hasPermission && antPathMatcher.match("/user/**", uri)) {
            hasPermission = true;
        }
        return hasPermission;
    }
}
