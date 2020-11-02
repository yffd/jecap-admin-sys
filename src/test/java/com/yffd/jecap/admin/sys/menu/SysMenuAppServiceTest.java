package com.yffd.jecap.admin.sys.menu;

import com.alibaba.fastjson.JSON;
import com.yffd.jecap.admin.sys.BaseTest;
import com.yffd.jecap.admin.sys.application.model.menu.SysMenuTree;
import com.yffd.jecap.admin.sys.application.service.SysMenuAppService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SysMenuAppServiceTest extends BaseTest {
    @Autowired private SysMenuAppService menuAppService;

    @Test
    public void modifyParentIdTest() {
        this.menuAppService.modifyParentId("1045", "1034");
    }

    @Test
    public void queryTreeTest() {
        List<SysMenuTree> tree = this.menuAppService.queryTree();
        System.out.println(JSON.toJSONString(tree));
    }
}
