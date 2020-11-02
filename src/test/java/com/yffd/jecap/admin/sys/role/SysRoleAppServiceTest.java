package com.yffd.jecap.admin.sys.role;

import com.yffd.jecap.admin.sys.BaseTest;
import com.yffd.jecap.admin.sys.application.service.SysRoleAppService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class SysRoleAppServiceTest extends BaseTest {
    @Autowired
    private SysRoleAppService roleAppService;

    @Test
    public void savePmsnTest() {
        Set<String> pmsnIds = new HashSet<>();
        pmsnIds.add("pmsn1");
        pmsnIds.add("pmsn2");
        pmsnIds.add("pmsn3");
    }

    @Test
    public void modifyRltPmsnTest() {
        String roleId = "1004";
        Set<String> addPmsnIds = new HashSet<>();
        addPmsnIds.add("1031");
        addPmsnIds.add("1032");
        addPmsnIds.add("1033");
        Set<String> removePmsnIds = null;
        this.roleAppService.modifyRltPmsn(roleId, addPmsnIds, removePmsnIds);
    }
}
