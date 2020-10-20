package com.yffd.jecap.admin.sys.domain.file;

import com.alibaba.fastjson.JSON;
import com.yffd.jecap.admin.base.page.PageData;
import com.yffd.jecap.admin.sys.BaseTest;
import com.yffd.jecap.admin.sys.domain.file.service.SysFileService;
import com.yffd.jecap.admin.sys.domain.file.entity.SysFile;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class SysFileServiceTest extends BaseTest {
    @Autowired private SysFileService fileService;

    @Test
    public void addTest() {
        for (int i = 0; i < 10; i++) {
            SysFile file = new SysFile();
            file.setFileName("测试"+i+".txt");
            file.setFileSize(111L);
            file.setLocalFileName(UUID.randomUUID().toString() + ".txt");
            file.setLocalFilePath("/xx/yy/zz.txt");
            this.fileService.add(file);
        }
    }

    @Test
    public void updateByIdTest() {
        SysFile file = new SysFile();
        file.setFileName("测试123.txt");
        file.setFileSize(222L);
        file.setLocalFileName(UUID.randomUUID().toString() + ".txt");
        file.setLocalFilePath("/222/yy/zz.txt");
        file.setRemarks("测试");
        file.setId("1315536875213049857");
        this.fileService.updateById(file);
    }

    @Test
    public void deleteByIdTest() {
        this.fileService.deleteById("1315536875213049857");
    }

    @Test
    public void queryByIdTest() {
        SysFile file = this.fileService.queryById("1315538913732943874");
        System.out.println(JSON.toJSONString(file));
    }

    @Test
    public void queryPageTest() {
        SysFile file = new SysFile();
        file.setFileName("测试");
        PageData<SysFile> data = this.fileService.queryPage(file, 1, 3);
        System.out.println(JSON.toJSONString(data));
    }
}
