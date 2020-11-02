package com.yffd.jecap.admin.sys.application.service;

import com.yffd.jecap.admin.sys.application.assembler.SysLoginAssembler;
import com.yffd.jecap.admin.sys.application.dto.login.LoginParam;
import com.yffd.jecap.admin.sys.application.dto.login.LoginToken;
import com.yffd.jecap.admin.sys.application.security.authc.token.AccessToken;
import com.yffd.jecap.admin.sys.domain.exception.SysException;
import com.yffd.jecap.admin.sys.domain.login.entity.SysLoginAcnt;
import com.yffd.jecap.admin.sys.domain.login.service.SysLoginAcntService;
import com.yffd.jecap.admin.sys.domain.user.entity.SysUser;
import com.yffd.jecap.admin.sys.domain.user.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Slf4j
@Service
@Transactional
public class SysLoginAppService {
    @Autowired private SysLoginAcntService loginAcntService;
    @Autowired private SysLoginParamService loginParamService;
    @Autowired private SysUserService userService;

    /**
     * 登录
     * @param loginToken
     * @return
     */
    public AccessToken doLogin(LoginToken loginToken) {
        if (null == loginToken) throw SysException.cast("登录失败").prompt();
        String loginName = loginToken.getLoginName();
        String loginPwd = loginToken.getLoginPwd();
        if (StringUtils.isAnyBlank(loginName, loginPwd))
            throw SysException.cast("登录失败").prompt();
        SysLoginAcnt loginAcnt = this.loginAcntService.queryByAcntName(loginName);
        if (null == loginAcnt) {
            log.info(String.format("登录账号不存在【%s】", loginName));
            throw SysException.cast("账号或密码错误").prompt();
        }
        if (SysLoginAcntService.ACNT_STATUS_INACTIVE.equals(loginAcnt.getAcntStatus()))
            throw SysException.cast("登录账户已冻结").prompt();
        boolean valid = this.loginAcntService.validPwd(loginPwd, loginAcnt.getAcntPwdSalt(), loginAcnt.getAcntPwd());
        if (!valid) {
            log.info(String.format("登录密码校验失败【账号=%s】", loginName));
            throw SysException.cast("账号或密码错误").prompt();
        }
        //登录成功后设置
        AccessToken accessToken = new AccessToken();
        this.loginSuccess(accessToken, loginAcnt);
        return accessToken;
    }

    /**
     * 登出
     * @param tokenValue
     */
    public void doLogout(String tokenValue) {
        if (StringUtils.isBlank(tokenValue)) return;
        this.loginParamService.clearLoginParam(tokenValue);
    }

    /**
     * 注册
     * @param loginToken
     */
    public void doRegister(LoginToken loginToken) {
        String loginName = loginToken.getLoginName();
        String loginPwd = loginToken.getLoginPwd();
        this.loginAcntService.addBy(new SysLoginAcnt(loginName, loginPwd));
    }

    /**
     * 登录成功后处理
     * @param accessToken
     * @param loginAcnt
     */
    private void loginSuccess(AccessToken accessToken, SysLoginAcnt loginAcnt) {
        loginAcnt.setLastLoginTime(LocalDateTime.now());
        loginAcnt.setLoginTimes(loginAcnt.getLoginTimes() + 1);
        this.loginAcntService.modifyById(loginAcnt);

        SysUser user = this.userService.queryByLoginAcntId(loginAcnt.getAcntId());
        LoginParam param = SysLoginAssembler.buildLoginParam(accessToken, loginAcnt, user);
        Set<String> roleCodes = this.userService.queryRltRoleCode(user.getUserId());
        Set<String> pmsnCodes = this.userService.queryRltPmsnCode(user.getUserId());
        param.setRoles(roleCodes).setPmsns(pmsnCodes);
        this.loginParamService.saveLoginParam(param);
    }


}
