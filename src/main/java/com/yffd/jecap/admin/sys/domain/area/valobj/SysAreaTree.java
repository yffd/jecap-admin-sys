package com.yffd.jecap.admin.sys.domain.area.valobj;

import com.alibaba.fastjson.annotation.JSONField;
import com.yffd.jecap.admin.base.support.tree.AbstractTreeBuilder;
import com.yffd.jecap.admin.base.support.tree.Treeable;
import com.yffd.jecap.admin.sys.domain.area.entity.SysArea;
import com.yffd.jecap.admin.sys.domain.constant.SysConsts;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

@Data
public class SysAreaTree implements Treeable<SysAreaTree> {
    private List<SysAreaTree> children;
    private String id;
    private String pid;
    private String areaLevel;
    private String areaName;
    private String areaJianpin;

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
    public List<SysAreaTree> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<SysAreaTree> children) {
        this.children = children;
    }

    public static SysAreaTree buildTree(List<SysArea> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        SysArea parent = new SysArea();
        parent.setId(SysConsts.DEF_TREE_ROOT_ID);
        return buildTree(list, parent);
    }

    public static SysAreaTree buildTree(List<SysArea> list, SysArea parent) {
        if (CollectionUtils.isEmpty(list)) return null;
        AbstractTreeBuilder<SysArea, SysAreaTree> builder = new AbstractTreeBuilder<SysArea, SysAreaTree>() {

            @Override
            public SysAreaTree convert(SysArea obj) {
                SysAreaTree tree = new SysAreaTree();
                tree.setId(obj.getId());
                tree.setPid(obj.getPid());
                tree.setAreaLevel(obj.getAreaLevel());
                tree.setAreaName(obj.getAreaName());
                tree.setAreaJianpin(obj.getAreaJianpin());
                return tree;
            }
        };
        return builder.buildTree(parent, list);
    }

}
