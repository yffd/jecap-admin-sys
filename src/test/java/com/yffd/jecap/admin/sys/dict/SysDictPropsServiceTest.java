package com.yffd.jecap.admin.sys.dict;

import com.alibaba.fastjson.JSON;
import com.yffd.jecap.admin.sys.BaseTest;
import com.yffd.jecap.admin.sys.domain.dict.entity.SysDictProps;
import com.yffd.jecap.admin.sys.domain.dict.service.SysDictPropsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SysDictPropsServiceTest extends BaseTest {
    @Autowired private SysDictPropsService dictPropsService;

    @Test
    public void addTest() {
        for (int i = 0; i < 5; i++) {
            SysDictProps props = new SysDictProps();
            props.setDictId("1315591847946596353");
            props.setPropsName("属性" + i);
            props.setPropsCode("props" + i);
            props.setPropsType("1");
            props.setPropsValue("嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻");
            this.dictPropsService.add(props);
        }

    }

    @Test
    public void updateByIdTest() {
        SysDictProps props = new SysDictProps();
        props.setPropsValue("xxxxxxxxx");
        props.setPropsType("2");
        props.setId("1315594065168928769");
        this.dictPropsService.updateById(props);
    }

    @Test
    public void deleteByIdTest() {
        this.dictPropsService.deleteById("1315595044442411010");
    }

    @Test
    public void deleteByDictIdTest() {
        this.dictPropsService.deleteByDictId("1315591847946596353");
    }

    @Test
    public void queryByIdTest() {
        SysDictProps props = this.dictPropsService.queryById("1315595044442411010");
        System.out.println(JSON.toJSONString(props));
    }

    @Test
    public void queryByDictIdTest() {
        List<SysDictProps> propsList = this.dictPropsService.queryByDictId("1315591847946596353");
        System.out.println(JSON.toJSONString(propsList));
        System.out.println(propsList.size());
    }
}
