package com.yffd.jecap.admin.sys.domain.pmsn.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.yffd.jecap.admin.base.support.tree.AbstractTreeBuilder;
import com.yffd.jecap.admin.base.support.tree.Treeable;
import com.yffd.jecap.admin.sys.domain.pmsn.entity.SysPmsn;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Data
public class SysPmsnTree implements Treeable<SysPmsnTree> {
    private List<SysPmsnTree> children;

    private String id;
    private String pid;
    private String pmsnName;
    private String pmsnCode;
    private String pmsnType;
    private String pmsnStatus;

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
    public List<SysPmsnTree> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<SysPmsnTree> children) {
        this.children = children;
    }

    public static List<SysPmsnTree> buildTree(List<SysPmsn> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        SysPmsn parent = new SysPmsn();
        parent.setPmsnId("-1");
        SysPmsnTree tree = buildTree(list, parent);
        if (null == tree) return Collections.emptyList();
        return tree.getChildren();
    }

    public static SysPmsnTree buildTree(List<SysPmsn> list, SysPmsn parent) {
        if (CollectionUtils.isEmpty(list)) return null;
        AbstractTreeBuilder<SysPmsn, SysPmsnTree> builder = new AbstractTreeBuilder<SysPmsn, SysPmsnTree>() {

            @Override
            public SysPmsnTree convert(SysPmsn obj) {
                SysPmsnTree tree = new SysPmsnTree();
                tree.setId(obj.getPmsnId());
                tree.setPid(obj.getParentId());
                tree.setPmsnCode(obj.getPmsnCode());
                tree.setPmsnName(obj.getPmsnName());
                tree.setPmsnStatus(obj.getPmsnStatus());
                tree.setPmsnType(obj.getPmsnType());
                return tree;
            }
        };
        return builder.buildTree(parent, list);
    }

}
