package com.yffd.jecap.admin.sys.user;

import com.alibaba.fastjson.JSON;
import com.yffd.jecap.admin.base.page.PageData;
import com.yffd.jecap.admin.sys.BaseTest;
import com.yffd.jecap.admin.sys.domain.user.entity.SysUser;
import com.yffd.jecap.admin.sys.domain.user.service.SysUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Set;

public class SysUserServiceTest extends BaseTest {
    @Autowired private SysUserService userService;

    @Test
    public void addBatchTest() {
        for (int i = 0; i < 25; i++) {
            SysUser user = new SysUser("zhangsan" + i, null);
            user.setUserName("李四" + i);
            user.setUserCode("lisi" + i);
            user.setUserPhone("1234567890" + i);
            user.setUserEmail(i + "12345@qq.com");
            user.setUserGender((i % 2 == 1) ? "F":"M");
            user.setCreateTime(LocalDateTime.now());
            user.setLoginAcntId((1025 + i) + "");
            this.userService.addBy(user);
        }
    }

    @Test
    public void addByTest() {
        SysUser user = new SysUser("zhangsan", null);
        user.setUserName("张三");
        user.setCreateTime(LocalDateTime.now());
        this.userService.addBy(user);
    }

    @Test
    public void modifyByIdTest() {
        SysUser user = new SysUser("zhangsan", null);
        user.setUserId("1315468442526130178");
        user.setUserName("张三123");
        this.userService.modifyById(user);
    }

    @Test
    public void removeByIdTest() {
        this.userService.removeById("1315468442526130178");
    }

    @Test
    public void queryByIdTest() {
        SysUser user = this.userService.queryById("1315471467227463682");
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    public void queryPageTest() {
        SysUser user = new SysUser();
        user.setUserName("张三");
//        user.setUserPhone("123123123");
        PageData<SysUser> data = this.userService.queryPage(user, 0, 5);
        System.out.println(JSON.toJSONString(data));
    }

    @Test
    public void disableUserTest() {
        this.userService.disableStatus("1315476332636852226");
    }

    @Test
    public void enableUserTest() {
        this.userService.enableStatus("1315476332636852226");
    }

    @Test
    public void queryRltRoleCodeTest() {
        Set<String> roleCodes = this.userService.queryRltRoleCode("1012");
        System.out.println(JSON.toJSONString(roleCodes));
    }

    @Test
    public void queryRltPmsnCodeTest() {
        Set<String> pmsnCodes = this.userService.queryRltPmsnCode("1012");
        System.out.println(JSON.toJSONString(pmsnCodes));
    }
}
