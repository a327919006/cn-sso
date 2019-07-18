# cn-sso

#### 介绍
cn-sso，项目整合SpringSecurity、SpringSecurityOauth2、SpringSecurityJwt，实现通用认证、授权模块。

#### Maven模块描述

| 端口 | 模块名称 | 描述 |
| --- | --- | --- |
| ----- | sso-server | 授权中心相关配置 |
| ----- | sso-client | 业务模块相关配置 |
| 10083 | sample-boot | 授权接口与业务接口在同一项目的示例 |
| 10083 | sample-server | 授权中心示例，授权接口与业务接口独立 |
| 10081 | sample-client | 业务模块示例，授权接口与业务接口独立 |

#### 登录流程
```
授权中心发放clientId和ClientSecret给业务模块（或接口网关）
业务模块收到客户端登录请求（账号、密码）
业务模块使用password模式调用授权中心token接口
授权中心验证账号、密码信息，返回JWT
```

#### 刷新Token流程
```
业务模块收到客户端刷新Token请求
业务模块使用refresh_token模式调用授权中心token接口
授权中心验证refresh_token信息，返回新JWT
```

#### 业务接口访问流程
```
业务模块启动时先去授权中心获取JWT密钥
客户端访问业务接口时，使用JWT密钥验证Token
再根据用户权限决定是否允许访问接口
```