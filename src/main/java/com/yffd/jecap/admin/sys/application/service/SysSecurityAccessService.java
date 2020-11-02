package com.yffd.jecap.admin.sys.application.service;

import com.yffd.jecap.admin.sys.application.dto.login.LoginParam;
import com.yffd.jecap.admin.sys.application.security.handler.ISecurityAccessHandler;
import com.yffd.jecap.admin.sys.domain.exception.SysException;
import com.yffd.jecap.admin.sys.domain.user.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class SysSecurityAccessService implements ISecurityAccessHandler {
    @Autowired private SysLoginParamService loginParamService;
    @Autowired private SysUserService userService;

    @Override
    public boolean validAccessToken(String tokenValue) throws Exception {
        LoginParam param = this.loginParamService.loadLoginParam(tokenValue);
        if (null == param) {
            log.warn(String.format("未找到有效的登录令牌【tokenValue=%s】", tokenValue));
            throw SysException.cast("登录失效，请重新登录").prompt();
        }
        return true;
    }

    @Override
    public Set<String> getPmsns(String tokenValue) {
        LoginParam param = this.loginParamService.loadLoginParam(tokenValue);
        if (null == param || StringUtils.isBlank(param.getUserId())) {
            log.warn(String.format("未找到有效的登录信息【tokenValue=%s】", tokenValue));
            throw SysException.cast("登录失效，请重新登录").prompt();
        }
        return this.userService.queryRltPmsnCode(param.getUserId());
    }

}
