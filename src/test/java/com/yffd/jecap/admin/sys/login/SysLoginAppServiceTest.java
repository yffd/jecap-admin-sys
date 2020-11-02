package com.yffd.jecap.admin.sys.login;

import com.alibaba.fastjson.JSON;
import com.yffd.jecap.admin.sys.BaseTest;
import com.yffd.jecap.admin.sys.application.dto.login.LoginToken;
import com.yffd.jecap.admin.sys.application.security.authc.token.AccessToken;
import com.yffd.jecap.admin.sys.application.service.SysLoginAppService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SysLoginAppServiceTest extends BaseTest {
    @Autowired private SysLoginAppService loginAppService;

    @Test
    public void doRegisterTest() {
        LoginToken token = new LoginToken("admin", "1234qwer");
        this.loginAppService.doRegister(token);

        for (int i = 0; i < 10; i++) {
            this.loginAppService.doRegister(new LoginToken("lisi" + i, "lisi" + i));
        }
    }

    @Test
    public void doLoginTest() {
        LoginToken token = new LoginToken("admin", "1234qwer");
        AccessToken accessToken = this.loginAppService.doLogin(token);
        System.out.println(JSON.toJSONString(accessToken));

        accessToken = this.loginAppService.doLogin(new LoginToken("lisi0", "lisi0"));
        System.out.println(JSON.toJSONString(accessToken));
    }

    @Test
    public void doLogoutTest() {
        this.loginAppService.doLogout("0db34bf3f2cd47789597ec60bb66eb42");
    }


}
