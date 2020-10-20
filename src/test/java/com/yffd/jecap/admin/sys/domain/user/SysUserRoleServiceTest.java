package com.yffd.jecap.admin.sys.domain.user;

import com.alibaba.fastjson.JSON;
import com.yffd.jecap.admin.sys.BaseTest;
import com.yffd.jecap.admin.sys.domain.user.service.SysUserRoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class SysUserRoleServiceTest extends BaseTest {
    @Autowired private SysUserRoleService userRoleService;

    @Test
    public void addRltTest() {
        this.userRoleService.addRlt("123", "345");

        Set<String> userIds = new HashSet<>();
        for (int i = 0; i <10; i++) {
            userIds.add("xxxxxxx" + i);
        }
        this.userRoleService.addRlt(userIds, "345");

        Set<String> roleIds = new HashSet<>();
        for (int i = 0; i <10; i++) {
            roleIds.add("xxxxxxx" + i);
        }
        this.userRoleService.addRlt("123", roleIds);
    }

    @Test
    public void updateRltTest() {
        Set<String> addRoleIds = new HashSet<>();
        addRoleIds.add("new_11");
        addRoleIds.add("new_12");
        Set<String> delRoleIds = new HashSet<>();
        delRoleIds.add("xxxxxxx9");
        delRoleIds.add("xxxxxxx8");
        this.userRoleService.updateRlt("123", addRoleIds, delRoleIds);
    }

    @Test
    public void deleteByTest() {
        this.userRoleService.deleteBy("123", "345");

        Set<String> roleIds = new HashSet<>();
        for (int i = 0; i <3; i++) {
            roleIds.add("xxxxxxx" + i);
        }
        this.userRoleService.deleteBy("123", roleIds);
    }

    @Test
    public void deleteByRoleIdTest() {
        this.userRoleService.deleteByRoleId("345");
    }

    @Test
    public void deleteByUserIdTest() {
        this.userRoleService.deleteByUserId("123");
    }

    @Test
    public void queryRoleIdsTest() {
        Set<String> ids = this.userRoleService.queryRoleIds("123");
        System.out.println(JSON.toJSONString(ids));
    }

    @Test
    public void queryUserIdsTest() {
        Set<String> ids = this.userRoleService.queryUserIds("345");
        System.out.println(JSON.toJSONString(ids));
    }
}
