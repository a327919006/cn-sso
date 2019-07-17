# cn-sso

#### 介绍
cn-sso，项目整合SpringSecurity、SpringSecurityOauth2、SpringSecurityJwt，实现通用认证、授权模块。

#### 登录流程
1. 认证中心发放clientId和Secret给网关（或业务模块）
2. 网关收到客户端登录请求（账号、密码）
3. 网关使用password模式调用认证中心token接口
4. 认证中心验证账号、密码信息，返回JWT

#### 刷新Token流程
1. 网关收到客户端刷新Token请求
2. 网关使用refresh_token模式调用认证中心token接口
3. 认证中心验证token信息，返回新JWT

