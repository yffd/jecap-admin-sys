package com.yffd.jecap.admin.sys.domain.group;

import com.alibaba.fastjson.JSON;
import com.yffd.jecap.admin.base.page.PageData;
import com.yffd.jecap.admin.sys.BaseTest;
import com.yffd.jecap.admin.sys.domain.group.entity.SysGroup;
import com.yffd.jecap.admin.sys.domain.group.service.SysGroupService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SysGroupServiceTest extends BaseTest {
    @Autowired private SysGroupService groupService;

    @Test
    public void addTest() {
        for (int i = 0; i < 10; i++) {
            SysGroup group = new SysGroup("用户组" + i, "user" + i, "1");
            this.groupService.add(group);
        }
    }

    @Test
    public void updateByIdTest() {
        SysGroup group = new SysGroup("角色组", "role", "2");
        group.setId("1315544402445316098");
        this.groupService.updateById(group);
    }

    @Test
    public void deleteByIdTest() {
        this.groupService.deleteById("1315544402445316098");
    }

    @Test
    public void queryByIdTest() {
        SysGroup group = this.groupService.queryById("1315544402696974338");
        System.out.println(JSON.toJSONString(group));
    }

    @Test
    public void queryByGroupCodeTest() {
        SysGroup group = this.groupService.queryByGroupCode("user1");
        System.out.println(JSON.toJSONString(group));
    }

    @Test
    public void queryPageTest() {
        SysGroup group = new SysGroup("用户组", "user1", "1");
        PageData<SysGroup> data = this.groupService.queryPage(group, 1, 3);
        System.out.println(JSON.toJSONString(data));
    }
}
