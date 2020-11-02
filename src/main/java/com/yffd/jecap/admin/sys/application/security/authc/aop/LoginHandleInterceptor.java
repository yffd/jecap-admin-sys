package com.yffd.jecap.admin.sys.application.security.authc.aop;

import com.yffd.jecap.admin.sys.application.security.authc.TokenInvalidException;
import com.yffd.jecap.admin.sys.application.security.handler.ISecurityAccessHandler;
import com.yffd.jecap.admin.sys.application.security.util.RequestUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Setter
public class LoginHandleInterceptor implements HandlerInterceptor {
    private boolean skip = false;
    private ISecurityAccessHandler securityAccessHandler;

    public LoginHandleInterceptor(ISecurityAccessHandler securityAccessHandler) {
        this.securityAccessHandler = securityAccessHandler;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (skip) {
            log.info("【拦截器】登录控制，禁用");
            return true;
        }
        String accessTokenKey = this.securityAccessHandler.getAccessTokenKey();
        String tokenValue = RequestUtils.getTokenValue(request, accessTokenKey);
        //校验令牌值的有效性
        if (StringUtils.isBlank(tokenValue)) {
            log.warn("登录令牌为空");
            throw TokenInvalidException.cast("登录已失效，请重新登录").prompt();
        }
        boolean flag = this.securityAccessHandler.validAccessToken(tokenValue);
        if (flag) {
            log.info("【拦截器】登录控制，登录有效");
        } else {
            log.info("【拦截器】登录控制，登录失效");
            throw TokenInvalidException.cast("登录已失效，请重新登录").prompt();
        }
        return flag;
    }

}
