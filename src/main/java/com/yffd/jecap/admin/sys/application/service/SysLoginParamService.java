package com.yffd.jecap.admin.sys.application.service;

import com.yffd.jecap.admin.sys.application.assembler.SysLoginAssembler;
import com.yffd.jecap.admin.sys.application.dto.login.LoginParam;
import com.yffd.jecap.admin.sys.application.security.util.RequestUtils;
import com.yffd.jecap.admin.sys.domain.login.entity.SysLoginInfo;
import com.yffd.jecap.admin.sys.domain.login.service.SysLoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLoginParamService {
    @Autowired private SysLoginInfoService loginInfoService;

    /**
     * 保存登录参数
     * @param param
     */
    public void saveLoginParam(LoginParam param) {
        SysLoginInfo info = SysLoginAssembler.buildLoginInfo(param);
        this.loginInfoService.addBy(info);
    }

    /**
     * 加载登录参数
     * @param tokenValue
     * @return
     */
    public LoginParam loadLoginParam(String tokenValue) {
        SysLoginInfo info = this.loginInfoService.queryByTokenValue(tokenValue);
        return SysLoginAssembler.buildLoginParam(info);
    }

    /**
     * 清空登录参数
     * @param tokenValue
     */
    public void clearLoginParam(String tokenValue) {
        this.loginInfoService.removeByTokenValue(tokenValue);
    }

}
