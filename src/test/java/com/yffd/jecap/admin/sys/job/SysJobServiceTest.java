package com.yffd.jecap.admin.sys.job;

import com.alibaba.fastjson.JSON;
import com.yffd.jecap.admin.base.page.PageData;
import com.yffd.jecap.admin.sys.BaseTest;
import com.yffd.jecap.admin.sys.domain.job.service.SysJobService;
import com.yffd.jecap.admin.sys.domain.job.entity.SysJob;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SysJobServiceTest extends BaseTest {
    @Autowired private SysJobService jobService;

    @Test
    public void addTest() {
        for (int i = 0; i < 10; i++) {
            SysJob job = new SysJob();
            job.setJobName("高级开发" + i);
            job.setJobCode("GJKF" + i);
            this.jobService.add(job);
        }
    }

    @Test
    public void updateByIdTest() {
        SysJob job = new SysJob();
        job.setJobName("中级开发");
        job.setJobCode("ZJKF");
        job.setId("1315546840585797633");
        this.jobService.updateById(job);
    }

    @Test
    public void deleteByIdTest() {
        this.jobService.deleteById("1315546840585797633");
    }

    @Test
    public void queryByIdTest() {
        SysJob job = this.jobService.queryById("1315546840766152706");
        System.out.println(JSON.toJSONString(job));
    }

    @Test
    public void queryPageTest() {
        SysJob job = new SysJob();
        job.setJobName("高级开发");
        job.setJobCode("GJKF1");
        PageData<SysJob> data = this.jobService.queryPage(job, 1, 3);
        System.out.println(JSON.toJSONString(data));
    }
}
