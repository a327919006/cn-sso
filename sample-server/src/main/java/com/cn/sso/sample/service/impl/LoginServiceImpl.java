package com.cn.sso.sample.service.impl;

import com.cn.sso.sample.model.po.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Chen Nan
 */
@Component
@Slf4j
public class LoginServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = new SysUser();
        sysUser.setSysUserId("11111111111");
        sysUser.setUserName(username);
        sysUser.setUserPwd(passwordEncoder.encode("123456"));
        sysUser.setAuthorities(AuthorityUtils.createAuthorityList("admin"));
        return sysUser;
    }
}
