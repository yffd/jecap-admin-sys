package com.yffd.jecap.admin.sys.rlt;

import com.yffd.jecap.admin.sys.BaseTest;
import com.yffd.jecap.admin.sys.domain.rlt.service.SysRoleUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SysRoleUserServiceTest extends BaseTest {
    @Autowired private SysRoleUserService roleUserService;

    @Test
    public void addByTest() {
        for (int i = 5; i < 9; i++) {
            this.roleUserService.addBy("1013", "100"+i);
        }
    }
}
