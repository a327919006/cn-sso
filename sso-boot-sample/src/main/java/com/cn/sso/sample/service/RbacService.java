package com.cn.sso.sample.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Chen Nan
 */
public interface RbacService {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);

}
