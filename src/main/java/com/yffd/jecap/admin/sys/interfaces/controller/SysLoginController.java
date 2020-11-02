package com.yffd.jecap.admin.sys.interfaces.controller;

import com.yffd.jecap.admin.base.result.RtnResult;
import com.yffd.jecap.admin.sys.application.security.authc.token.AccessToken;
import com.yffd.jecap.admin.sys.application.dto.login.LoginToken;
import com.yffd.jecap.admin.sys.application.service.SysLoginAppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "【系统-登录】模块")
@RestController
@RequestMapping("/login")
public class SysLoginController {
    @Autowired private SysLoginAppService loginAppService;

    @ApiOperation(value = "登录")
    @PostMapping("/doLogin")
    public RtnResult doLogin(@RequestBody LoginToken model) {
        AccessToken accessToken = this.loginAppService.doLogin(model);
        return RtnResult.OK(accessToken);
    }

    @ApiOperation(value = "登出")
    @PostMapping("/doLogout")
    public RtnResult doLogout(@RequestBody AccessToken model) {
        this.loginAppService.doLogout(model.getTokenValue());
        return RtnResult.OK();
    }

    @ApiOperation(value = "注册")
    @PostMapping("/doRegister")
    public RtnResult doRegister(@RequestBody LoginToken model) {
        this.loginAppService.doRegister(model);
        return RtnResult.OK();
    }
}
