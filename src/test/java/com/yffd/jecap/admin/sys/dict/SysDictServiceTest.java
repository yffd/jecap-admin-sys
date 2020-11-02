package com.yffd.jecap.admin.sys.dict;

import com.alibaba.fastjson.JSON;
import com.yffd.jecap.admin.sys.BaseTest;
import com.yffd.jecap.admin.sys.domain.dict.entity.SysDict;
import com.yffd.jecap.admin.sys.domain.dict.service.SysDictService;
import com.yffd.jecap.admin.sys.domain.dict.valobj.SysDictTree;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SysDictServiceTest extends BaseTest {
    @Autowired private SysDictService dictService;

    @Test
    public void addRootTest() {
        SysDict dict = new SysDict("根字典", "dict");
        this.dictService.addRoot(dict);
    }

    @Test
    public void addChildTest() {
        {
            SysDict dict = new SysDict("一级项-01", "xx-01");
            dict.setPid("1315587562009530369");
            this.dictService.addChild(dict);

            SysDict dict1 = new SysDict("二级项-01-01", "xx-01-01");
            dict1.setPid(dict.getId());
            this.dictService.addChild(dict1);

            SysDict dict2 = new SysDict("二级项-01-02", "xx-01-02");
            dict2.setPid(dict.getId());
            this.dictService.addChild(dict2);

            SysDict dict3 = new SysDict("三级项-01-02-01", "xx-01-02-01");
            dict3.setPid(dict2.getId());
            this.dictService.addChild(dict3);
        }

        {
            SysDict dict = new SysDict("一级项-02", "yy-02");
            dict.setPid("1315587562009530369");
            this.dictService.addChild(dict);

            SysDict dict1 = new SysDict("二级项-02-01", "yy-02-01");
            dict1.setPid(dict.getId());
            this.dictService.addChild(dict1);

            SysDict dict2 = new SysDict("二级项-02-02", "yy-02-02");
            dict2.setPid(dict.getId());
            this.dictService.addChild(dict2);

            SysDict dict3 = new SysDict("三级项-02-02-01", "yy-02-02-01");
            dict3.setPid(dict2.getId());
            this.dictService.addChild(dict3);
        }

        {
            SysDict dict = new SysDict("一级项-03", "zz-03");
            dict.setPid("1315587562009530369");
            this.dictService.addChild(dict);

            SysDict dict1 = new SysDict("二级项-03-01", "zz-03-01");
            dict1.setPid(dict.getId());
            this.dictService.addChild(dict1);

            SysDict dict2 = new SysDict("二级项-03-02", "zz-03-02");
            dict2.setPid(dict.getId());
            this.dictService.addChild(dict2);

            SysDict dict3 = new SysDict("三级项-03-02-01", "zz-03-02-01");
            dict3.setPid(dict2.getId());
            this.dictService.addChild(dict3);

            SysDict dict4 = new SysDict("四级项-03-02-01-01", "zz-03-02-01-01");
            dict4.setPid(dict3.getId());
            this.dictService.addChild(dict4);
        }
    }

    @Test
    public void updateByIdTest() {
        SysDict dict = new SysDict("aa四级项-03-02-01-01", "aa-03-02-01-01");
        dict.setId("1315591848483467265");
        this.dictService.updateById(dict);
    }

    @Test
    public void deleteByIdTest() {
        this.dictService.deleteById("1315591848080814081");
    }

    @Test
    public void queryTreeTest() {
        List<SysDictTree> tree = this.dictService.queryTree();
        System.out.println(JSON.toJSONString(tree));

        SysDictTree data = this.dictService.queryTree("1315591846923186177");
        System.out.println(JSON.toJSONString(data));
    }

}
