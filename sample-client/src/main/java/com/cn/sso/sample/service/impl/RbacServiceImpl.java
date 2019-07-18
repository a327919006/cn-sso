package com.cn.sso.sample.service.impl;

import com.cn.sso.sample.service.RbacService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义权限校验服务
 *
 * @author Chen Nan
 */
@Component
@Slf4j
public class RbacServiceImpl implements RbacService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        log.info("【校验权限】");
        Object principal = authentication.getPrincipal();

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
