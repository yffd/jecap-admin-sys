package com.yffd.jecap.admin.sys.domain.org.valobj;

import com.alibaba.fastjson.annotation.JSONField;
import com.yffd.jecap.admin.sys.domain.constant.SysConsts;
import com.yffd.jecap.admin.sys.domain.org.entity.SysOrg;
import com.yffd.jecap.admin.base.support.tree.AbstractTreeBuilder;
import com.yffd.jecap.admin.base.support.tree.Treeable;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

@Data
public class SysOrgTree implements Treeable<SysOrgTree> {

    private String id;
    private String pid;
    private String orgName;
    private String orgCode;
    private String orgSn;
    private List<SysOrgTree> children;

    @JSONField(serialize = false)
    @Override
    public Object getIdValue() {
        return id;
    }
    @JSONField(serialize = false)
    @Override
    public Object getPidValue() {
        return pid;
    }

    @Override
    public List<SysOrgTree> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<SysOrgTree> children) {
        this.children = children;
    }

    public static SysOrgTree buildTree(List<SysOrg> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        SysOrg parent = new SysOrg();
        parent.setId(SysConsts.DEF_TREE_ROOT_ID);
        return buildTree(list, parent);
    }

    public static SysOrgTree buildTree(List<SysOrg> list, SysOrg parent) {
        if (CollectionUtils.isEmpty(list)) return null;
        AbstractTreeBuilder<SysOrg, SysOrgTree> builder = new AbstractTreeBuilder<SysOrg, SysOrgTree>() {

            @Override
            public SysOrgTree convert(SysOrg obj) {
                SysOrgTree tree = new SysOrgTree();
                tree.setId(obj.getId());
                tree.setPid(obj.getPid());
                tree.setOrgName(obj.getOrgName());
                tree.setOrgCode(obj.getOrgCode());
                tree.setOrgSn(obj.getOrgSn());
                return tree;
            }
        };
        return builder.buildTree(parent, list);
    }

}
