package com.yffd.jecap.admin.sys.domain.org;

import com.alibaba.fastjson.JSON;
import com.yffd.jecap.admin.sys.BaseTest;
import com.yffd.jecap.admin.sys.domain.org.entity.SysOrg;
import com.yffd.jecap.admin.sys.domain.org.service.SysOrgService;
import com.yffd.jecap.admin.sys.domain.org.valobj.SysOrgTree;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SysOrgServiceTest extends BaseTest {
    @Autowired private SysOrgService orgService;

    @Test
    public void addRootTest() {
        SysOrg org = new SysOrg("测试机构", "test");
        this.orgService.addRoot(org);
    }

    @Test
    public void addChildTest() {
        {
            SysOrg org = new SysOrg("测试部门", "depart-01");
            org.setPid("1315567862823378945");
            this.orgService.addChild(org);

            SysOrg org1 = new SysOrg("测试组-01", "test-01");
            org1.setPid(org.getId());
            this.orgService.addChild(org1);

            SysOrg org2 = new SysOrg("测试组-02", "test-02");
            org2.setPid(org.getId());
            this.orgService.addChild(org2);
        }

        {
            SysOrg org = new SysOrg("研发部门", "depart-02");
            org.setPid("1315567862823378945");
            this.orgService.addChild(org);

            SysOrg org1 = new SysOrg("研发组-01", "dev-01");
            org1.setPid(org.getId());
            this.orgService.addChild(org1);

            SysOrg org2 = new SysOrg("研发组-02", "dev-02");
            org2.setPid(org.getId());
            this.orgService.addChild(org2);
        }

        /*{
            String pid = "1315567931152740354";
            SysOrg org = new SysOrg("研发组-02-01", "dev-02-01");
            org.setPid(pid);
            this.orgService.addChild(org);

            SysOrg org1 = new SysOrg("研发组-02-02", "dev-02-02");
            org1.setPid(pid);
            this.orgService.addChild(org1);

            SysOrg org2 = new SysOrg("研发组-02-03", "dev-02-03");
            org2.setPid(pid);
            this.orgService.addChild(org2);
        }*/
    }

    @Test
    public void updateByIdTest() {
        SysOrg org = new SysOrg("研发组-02", "dev-02");
        org.setId("1315567931152740354");
//        this.orgService.updateById(org);
        this.orgService.updateById(org, "1315567930930442241");
    }

    @Test
    public void deleteByIdTest() {
        this.orgService.deleteById("1315563497089679362");
    }

    @Test
    public void queryByIdTest() {
        SysOrg org = this.orgService.queryById("1315553772553924609");
        System.out.println(JSON.toJSONString(org));
    }

    @Test
    public void queryByOrgCodeTest() {
        SysOrg org = this.orgService.queryByOrgCode("dev-02");
        System.out.println(JSON.toJSONString(org));
    }

    @Test
    public void queryByPathTest() {
        List<SysOrg> orgs = this.orgService.queryByPath("1315550981605400578,1315553772553924609");
        System.out.println(JSON.toJSONString(orgs));
        System.out.println(orgs.size());
    }

    @Test
    public void queryTreeTest() {
        List<SysOrgTree> treeList = this.orgService.queryTree();
        System.out.println(JSON.toJSONString(treeList));

//        SysOrgTree tree = this.orgService.queryTree("1315563576571752449");
//        System.out.println(JSON.toJSONString(tree));
    }
}
