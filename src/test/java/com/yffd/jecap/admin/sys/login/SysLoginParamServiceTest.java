package com.yffd.jecap.admin.sys.login;

import com.alibaba.fastjson.JSON;
import com.yffd.jecap.admin.sys.BaseTest;
import com.yffd.jecap.admin.sys.application.dto.login.LoginParam;
import com.yffd.jecap.admin.sys.application.service.SysLoginParamService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class SysLoginParamServiceTest extends BaseTest {
    @Autowired private SysLoginParamService loginParamService;

    @Test
    public void saveLoginParamTest() {
        LoginParam param = new LoginParam();
        param.setUserId("11111");
        param.getParams().put("aa", "11");
        param.getParams().put("bb", "11");
        param.getParams().put("cc", LocalDateTime.now());
        this.loginParamService.saveLoginParam(param);
    }

    @Test
    public void loadLoginParamTest() {
        LoginParam param = this.loginParamService.loadLoginParam("0ce442a8471f465583ab04bbe72c6ce1");
        System.out.println(JSON.toJSONString(param));
    }

    @Test
    public void clearLoginParamTest() {
        this.loginParamService.clearLoginParam("b57c0c8de45c4ff6bb53de7f2e0c88e6");
    }
}
