package com.yffd.jecap.admin.sys.domain.area;

import com.alibaba.fastjson.JSON;
import com.yffd.jecap.admin.sys.BaseTest;
import com.yffd.jecap.admin.sys.domain.area.entity.SysArea;
import com.yffd.jecap.admin.sys.domain.area.service.SysAreaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SysAreaServiceTest extends BaseTest {
    @Autowired private SysAreaService areaService;

    @Test
    public void addRootTest() {
        SysArea area = new SysArea("1", "10000000");
        area.setAreaName("中国");
        area.setAreaJianpin("ZH");
        this.areaService.addRoot(area);
    }

    @Test
    public void addChildTest() {
        {
            SysArea area = new SysArea("3", "10000100");
            area.setAreaName("北京市");
            area.setAreaJianpin("BJ");
            area.setPid("1315852423444688897");
            this.areaService.addChild(area);

            SysArea area1 = new SysArea("4", "10000101");
            area1.setAreaName("通州区");
            area1.setAreaJianpin("TZQ");
            area1.setPid(area.getId());
            this.areaService.addChild(area1);
        }

        {
            SysArea area = new SysArea("2", "10010000");
            area.setAreaName("黑龙江省");
            area.setAreaJianpin("HLJ");
            area.setPid("1315852423444688897");
            this.areaService.addChild(area);

            SysArea area1 = new SysArea("3", "10010100");
            area1.setAreaName("哈尔滨市");
            area1.setAreaJianpin("HEB");
            area1.setPid(area.getId());
            this.areaService.addChild(area1);

            SysArea area2 = new SysArea("4", "10010101");
            area2.setAreaName("道外区");
            area2.setAreaJianpin("DWQ");
            area2.setPid(area1.getId());
            this.areaService.addChild(area2);
        }
    }

    @Test
    public void updateByIdTest() {
        SysArea area = new SysArea();
        area.setAreaLevel("3");
        area.setAreaName("齐齐哈尔市");
        area.setAreaJianpin("QQHE");
        area.setId("1315841525690707969");
        this.areaService.updateById(area);
    }

    @Test
    public void deleteByIdTest() {
        this.areaService.deleteById("1315852534182621185");
    }

    @Test
    public void queryByAreaNameTest() {
        SysArea area = this.areaService.queryByAreaName("北京市");
        System.out.println(JSON.toJSONString(area));
    }

    @Test
    public void queryByAreaLevelTest() {
        List<SysArea> data = this.areaService.queryByAreaLevel("1");
        System.out.println(JSON.toJSONString(data));
    }

    @Test
    public void queryAllTest() {
        List<SysArea> data = this.areaService.queryAll();
        System.out.println(JSON.toJSONString(data));
    }
}
