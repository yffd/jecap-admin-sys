package com.yffd.jecap.admin.sys.domain.user;

import com.alibaba.fastjson.JSON;
import com.yffd.jecap.admin.base.page.PageData;
import com.yffd.jecap.admin.sys.BaseTest;
import com.yffd.jecap.admin.sys.domain.user.service.SysUserService;
import com.yffd.jecap.admin.sys.domain.user.entity.SysUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class SysUserServiceTest extends BaseTest {
    @Autowired private SysUserService userService;

    @Test
    public void addBatchTest() {
        for (int i = 0; i < 55; i++) {
            SysUser user = new SysUser("zhangsan" + i, null);
            user.setUserName("张三" + i);
            user.setUserPhone("1234567890" + i);
            user.setUserEmail(i + "12345@qq.com");
            user.setCreateBy("test");
            user.setCreateTime(LocalDateTime.now());
            this.userService.add(user);
        }
    }

    @Test
    public void addTest() {
        SysUser user = new SysUser("zhangsan", null);
        user.setUserName("张三");
        user.setCreateBy("test");
        user.setCreateTime(LocalDateTime.now());
        this.userService.add(user);
    }

    @Test
    public void updateByIdTest() {
        SysUser user = new SysUser("zhangsan", null);
        user.setId("1315468442526130178");
        user.setUserName("张三123");
        this.userService.updateById(user);
    }

    @Test
    public void deleteByIdTest() {
        this.userService.deleteById("1315468442526130178");
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
    public void queryByAcntNameTest() {
        SysUser user = this.userService.queryByAcntName("zhangsan0");
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    public void disableUserTest() {
        this.userService.disableUser("1315476332636852226");
    }

    @Test
    public void enableUserTest() {
        this.userService.enableUser("1315476332636852226");
    }

    @Test
    public void encryptUserTest() {
        SysUser user = new SysUser("zhangsan", "1234qwer");
        this.userService.encryptUser(user);
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    public void checkPwdTest() {
        boolean flag = this.userService.checkPwd("1234qwer", "lwb0LhHDLoOZ4Khk2conYQ==", "zhangsan4d78697131114392acc84c69244ccbb3");
        System.out.println(flag);
    }

    @Test
    public void updatePwdTest() {
        this.userService.updatePwd("zhangsan0", "1234qwer");
    }

    @Test
    public void rltRoleTest() {
        this.userService.rltRole("1315476332636852226", "admin");
    }
}
