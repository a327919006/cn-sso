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
 * 自定义登录服务
 * 返回实现UserDetails接口的对象即可，本示例自定义了SysUser类
 *
 * @author Chen Nan
 */
@Component
@Slf4j
public class LoginServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("【用户登录】username={}", username);
        // 真实项目应该从数据库中获取用户信息，然后校验密码是否正确
        SysUser sysUser = new SysUser();
        sysUser.setSysUserId("11111111111");
        sysUser.setUserName(username);
        sysUser.setUserPwd(passwordEncoder.encode("123456"));
        sysUser.setUserStatus((byte) 1);
        sysUser.setAuthorities(AuthorityUtils.createAuthorityList("admin"));
        return sysUser;
    }
}
