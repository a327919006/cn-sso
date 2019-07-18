package com.cn.sso.client.model;

import cn.hutool.json.JSONUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Chen Nan
 */
@Data
public class SsoUserLoginDTO implements Serializable {
    private String username;
    private String password;

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
