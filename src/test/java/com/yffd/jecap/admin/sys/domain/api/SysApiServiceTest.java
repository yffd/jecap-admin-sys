package com.yffd.jecap.admin.sys.domain.api;

import com.alibaba.fastjson.JSON;
import com.yffd.jecap.admin.base.page.PageData;
import com.yffd.jecap.admin.sys.BaseTest;
import com.yffd.jecap.admin.sys.domain.api.entity.SysApi;
import com.yffd.jecap.admin.sys.domain.api.service.SysApiService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SysApiServiceTest extends BaseTest {
    @Autowired private SysApiService apiService;

    @Test
    public void addBatchTest() {
        for (int i = 0; i < 10; i++) {
            SysApi api = new SysApi();
            api.setApiName("用户管理-添加" + i);
            api.setApiUrl("user/add/" + i);
            this.apiService.add(api);
        }

    }

    @Test
    public void addTest() {
        SysApi api = new SysApi();
        api.setApiName("用户管理-添加");
        api.setApiUrl("user/add");
        this.apiService.add(api);
    }

    @Test
    public void updateByIdTest() {
        SysApi api = new SysApi();
        api.setApiName("用户管理-修改");
        api.setApiUrl("user/update");
        api.setId("1315532930252070914");
        this.apiService.updateById(api);
    }

    @Test
    public void deleteByIdTest() {
        this.apiService.deleteById("1315532930252070914");
    }

    @Test
    public void queryByIdTest() {
        SysApi api = this.apiService.queryById("1315532931971735553");
        System.out.println(JSON.toJSONString(api));
    }

    @Test
    public void queryPageTest() {
        SysApi api = new SysApi();
        api.setApiName("用户管理-修改");
        api.setApiUrl("user/update");
        PageData<SysApi> data = this.apiService.queryPage(api, 1, 3);
        System.out.println(JSON.toJSONString(data));
    }

}
