package com.yffd.jecap.admin.sys.domain.pmsn;

import com.alibaba.fastjson.JSON;
import com.yffd.jecap.admin.base.page.PageData;
import com.yffd.jecap.admin.sys.BaseTest;
import com.yffd.jecap.admin.sys.domain.pmsn.service.SysPmsnService;
import com.yffd.jecap.admin.sys.domain.pmsn.entity.SysPmsn;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SysPmsnServiceTest extends BaseTest {
    @Autowired private SysPmsnService pmsnService;

    @Test
    public void addBathTest() {
        for (int i = 0; i <10; i++) {
            SysPmsn pmsn = new SysPmsn("用户管理-添加" + i, "user-add-" + i, "1", "1");
            this.pmsnService.add(pmsn);
        }

    }

    @Test
    public void addTest() {
        SysPmsn pmsn = new SysPmsn("用户管理-添加", "user-add","1", "1");
        this.pmsnService.add(pmsn);
    }

    @Test
    public void updateByIdTest() {
        SysPmsn pmsn = new SysPmsn("用户管理-修改", "2", "0");
        pmsn.setId("1315529447771746305");
        this.pmsnService.updateById(pmsn);
    }

    @Test
    public void deleteByIdTest() {
        this.pmsnService.deleteById("1315529447771746305");
    }

    @Test
    public void queryByIdTest() {
        SysPmsn pmsn = this.pmsnService.queryById("1315529449600462849");
        System.out.println(JSON.toJSONString(pmsn));
    }

    @Test
    public void queryPageTest() {
        SysPmsn pmsn = new SysPmsn("用户管理", "1", "1");
        PageData<SysPmsn> data = this.pmsnService.queryPage(pmsn, 1, 3);
        System.out.println(JSON.toJSONString(data));
    }

    @Test
    public void updatePmsnNameTest() {
        this.pmsnService.updatePmsnName("1315529449600462849", "用户管理-修改");
    }
}
