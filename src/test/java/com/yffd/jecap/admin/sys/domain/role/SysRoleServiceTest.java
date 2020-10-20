package com.yffd.jecap.admin.sys.domain.role;

import com.alibaba.fastjson.JSON;
import com.yffd.jecap.admin.base.page.PageData;
import com.yffd.jecap.admin.sys.BaseTest;
import com.yffd.jecap.admin.sys.domain.role.entity.SysRole;
import com.yffd.jecap.admin.sys.domain.role.service.SysRoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class SysRoleServiceTest extends BaseTest {
    @Autowired private SysRoleService roleService;

    @Test
    public void addBatchTest() {
        for (int i = 0; i < 10; i++) {
            SysRole role = new SysRole("管理员" + i, "admin" + i);
            this.roleService.add(role);
        }
    }

    @Test
    public void addTest() {
        SysRole role = new SysRole("管理员", "admin");
        this.roleService.add(role);
    }

    @Test
    public void updateByIdTest() {
        SysRole role = new SysRole("管理员-1", "admin-1");
        role.setRoleStatus("0");
        role.setId("1315500201410162689");
        this.roleService.updateById(role);
    }

    @Test
    public void deleteByIdTest() {
        this.roleService.deleteById("1315500201410162689");
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
    public void updateStatusTest() {
        this.roleService.updateStatus("1315500513264939009", "0");
    }

    @Test
    public void saveGroupTest() {
        Set<String> groupIds = new HashSet<>();
        groupIds.add("g1");
        groupIds.add("g2");
        groupIds.add("g3");
        this.roleService.saveGroup("1315500513264939009", groupIds);
    }

    @Test
    public void savePmsnTest() {
        Set<String> pmsnIds = new HashSet<>();
        pmsnIds.add("pmsn1");
        pmsnIds.add("pmsn2");
        pmsnIds.add("pmsn3");
        this.roleService.savePmsn("1315500513264939009", pmsnIds);
    }
}
