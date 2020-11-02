package com.yffd.jecap.admin.sys.pmsn;

import com.alibaba.fastjson.JSON;
import com.yffd.jecap.admin.sys.BaseTest;
import com.yffd.jecap.admin.sys.domain.pmsn.entity.SysPmsn;
import com.yffd.jecap.admin.sys.domain.pmsn.service.SysPmsnService;
import com.yffd.jecap.admin.sys.domain.pmsn.vo.SysPmsnTree;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SysPmsnServiceTest extends BaseTest {
    @Autowired private SysPmsnService pmsnService;

    @Test
    public void addRootTest() {
        SysPmsn entity = new SysPmsn("根权限", "pmsn-root");
        this.pmsnService.addRoot(entity);
    }

    @Test
    public void addChildTest() {
        String pid = "1026";
        {
            SysPmsn entity = new SysPmsn("根权-0", "pmsn-0");
            entity.setParentId(pid);
            this.pmsnService.addChild(entity);

            SysPmsn entity1 = new SysPmsn("根权-0-1", "pmsn-0-1");
            entity1.setParentId(entity.getPmsnId());
            this.pmsnService.addChild(entity1);

            SysPmsn entity2 = new SysPmsn("根权-0-2", "pmsn-0-2");
            entity2.setParentId(entity.getPmsnId());
            this.pmsnService.addChild(entity2);

            SysPmsn entity3 = new SysPmsn("根权-0-3", "pmsn-0-3");
            entity3.setParentId(entity.getPmsnId());
            this.pmsnService.addChild(entity3);
        }

        {
            SysPmsn entity1 = new SysPmsn("根权-1", "pmsn-1");
            entity1.setParentId(pid);
            this.pmsnService.addChild(entity1);

            SysPmsn entity2 = new SysPmsn("根权-1-1", "pmsn-1-1");
            entity2.setParentId(entity1.getPmsnId());
            this.pmsnService.addChild(entity2);

            entity2 = new SysPmsn("根权-1-2", "pmsn-1-2");
            entity2.setParentId(entity1.getPmsnId());
            this.pmsnService.addChild(entity2);

            entity2 = new SysPmsn("根权-1-3", "pmsn-1-3");
            entity2.setParentId(entity1.getPmsnId());
            this.pmsnService.addChild(entity2);

            entity2 = new SysPmsn("根权-1-4", "pmsn-1-4");
            entity2.setParentId(entity1.getPmsnId());
            this.pmsnService.addChild(entity2);

            SysPmsn entity3 = new SysPmsn("根权-1-4-1", "pmsn-1-4-1");
            entity3.setParentId(entity2.getPmsnId());
            this.pmsnService.addChild(entity3);
        }

        {
            SysPmsn entity1 = new SysPmsn("根权-2", "pmsn-2");
            entity1.setParentId(pid);
            this.pmsnService.addChild(entity1);

            SysPmsn entity2 = new SysPmsn("根权-2-1", "pmsn-2-1");
            entity2.setParentId(entity1.getPmsnId());
            this.pmsnService.addChild(entity2);

            entity2 = new SysPmsn("根权-2-2", "pmsn-2-2");
            entity2.setParentId(entity1.getPmsnId());
            this.pmsnService.addChild(entity2);

            entity2 = new SysPmsn("根权-2-3", "pmsn-2-3");
            entity2.setParentId(entity1.getPmsnId());
            this.pmsnService.addChild(entity2);

            entity2 = new SysPmsn("根权-2-4", "pmsn-2-4");
            entity2.setParentId(entity1.getPmsnId());
            this.pmsnService.addChild(entity2);

            SysPmsn entity3 = new SysPmsn("根权-2-4-1", "pmsn-2-4-1");
            entity3.setParentId(entity2.getPmsnId());
            this.pmsnService.addChild(entity3);
        }
    }

    @Test
    public void queryListTest() {
        List<SysPmsn> entitys = this.pmsnService.queryAll();
        List<SysPmsnTree> treeList = SysPmsnTree.buildTree(entitys);
        System.out.println(JSON.toJSONString(treeList));
        System.out.println(entitys.size());
    }

    @Test
    public void modifyBatchPmsnStatusTest() {
        Set<String> pmsnIds = new HashSet<>();
        pmsnIds.add("1002");
        pmsnIds.add("1003");
        pmsnIds.add("1004");
        this.pmsnService.modifyBatchPmsnStatus(pmsnIds, "1");
    }


}
