package com.yffd.jecap.admin.sys.role;

import com.alibaba.fastjson.JSON;
import com.yffd.jecap.admin.base.page.PageData;
import com.yffd.jecap.admin.sys.BaseTest;
import com.yffd.jecap.admin.sys.domain.role.entity.SysRole;
import com.yffd.jecap.admin.sys.domain.role.service.SysRoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SysRoleServiceTest extends BaseTest {
    @Autowired private SysRoleService roleService;

    @Test
    public void addBatchTest() {
        for (int i = 0; i < 10; i++) {
            SysRole role = new SysRole("管理员" + i, "admin" + i);
            this.roleService.addBy(role);
        }
    }

    @Test
    public void addByTest() {
        SysRole role = new SysRole("管理员", "admin");
        this.roleService.addBy(role);
    }

    @Test
    public void modifyByIdTest() {
        SysRole role = new SysRole("管理员-1", "admin-1");
        role.setRoleStatus("0");
        role.setRoleId("1315500201410162689");
        this.roleService.modifyById(role);
    }

    @Test
    public void removeByIdTest() {
        this.roleService.removeById("1315500201410162689");
    }

    @Test
    public void queryByIdTest() {
        SysRole role = this.roleService.queryById("1315500513264939009");
        System.out.println(JSON.toJSONString(role));
    }

    @Test
    public void queryPageTest() {
        SysRole role = new SysRole("管理员0", "admin0");
        PageData<SysRole> data = this.roleService.queryPage(role, 1, 5);
        System.out.println(JSON.toJSONString(data));
    }

    @Test
    public void modifyStatusTest() {
        this.roleService.modifyStatus("1315500513264939009", "0");
    }


}
