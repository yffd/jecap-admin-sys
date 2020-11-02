package com.yffd.jecap.admin.sys.application.security.authz.aop;

import com.alibaba.fastjson.JSON;
import com.yffd.jecap.admin.sys.application.security.authz.AuthzInvalidException;
import com.yffd.jecap.admin.sys.application.security.authz.annotation.Logical;
import com.yffd.jecap.admin.sys.application.security.authz.annotation.RequiresPmsns;
import com.yffd.jecap.admin.sys.application.security.handler.ISecurityAccessHandler;
import com.yffd.jecap.admin.sys.application.security.util.RequestUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

@Slf4j
@Setter
public class RequiresPmsnsAnnotationMethodInterceptor implements HandlerInterceptor {
    private boolean skip = false;
    private ISecurityAccessHandler securityAccessHandler;

    public RequiresPmsnsAnnotationMethodInterceptor(ISecurityAccessHandler securityAccessHandler) {
        this.securityAccessHandler = securityAccessHandler;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (skip) {
            log.info("【拦截器】权限控制，禁用");
            return true;
        }
        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RequiresPmsns methodAnnotation = handlerMethod.getMethodAnnotation(RequiresPmsns.class);
            if (null != methodAnnotation) {
                Logical logical = methodAnnotation.logical();
                String[] value = methodAnnotation.value();
                String tokenValue = RequestUtils.getTokenValue(request, this.securityAccessHandler.getAccessTokenKey());
                if (StringUtils.isBlank(tokenValue)) {
                    log.warn("登录令牌为空");
                    throw AuthzInvalidException.cast("登录已失效，请重新登录").prompt();
                }
                Set<String> pmsns = this.securityAccessHandler.getPmsns(tokenValue);
                boolean valid = this.valid(pmsns, value, logical);
                if (valid) {
                    log.info(String.format("【拦截器】权限控制，验证通过，访问方法=%s，需要权限=%s", handlerMethod.toString(), JSON.toJSONString(value)));
                } else {
                    log.info(String.format("【拦截器】权限控制，验证失败，访问方法=%s，需要权限=%s", handlerMethod.toString(), JSON.toJSONString(value)));
                    throw AuthzInvalidException.cast("非法访问").prompt();
                }
            } else {
                log.info(String.format("【拦截器】权限控制，验证跳过，访问方法=%s，需要权限=[]", handlerMethod.toString()));
            }
        }
        return true;
    }

    private boolean valid(Set<String> pmsns, String[] value, Logical logical) {
        if (null != value && value.length > 0) {
            if (CollectionUtils.isEmpty(pmsns)) return false;
            if (Logical.AND == logical) {
                for (int i = 0; i < value.length; i++) {
                    if (!pmsns.contains(value[i])) {
                        return false;
                    }
                }
            } else {
                //OR
                for (int i = 0; i < value.length; i++) {
                    if (pmsns.contains(value[i])) {
                        return true;
                    }
                }
            }
        }
        return true;
    }

}
