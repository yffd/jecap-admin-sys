package com.yffd.jecap.admin.sys.application.model.menu;

import com.alibaba.fastjson.annotation.JSONField;
import com.yffd.jecap.admin.sys.domain.menu.entity.SysMenu;
import com.yffd.jecap.admin.base.support.tree.AbstractTreeBuilder;
import com.yffd.jecap.admin.base.support.tree.Treeable;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

@Data
public class SysMenuTree implements Treeable<SysMenuTree> {
    private List<SysMenuTree> children;

    private String menuId;
    private String parentId;
    private String menuName;
    private String menuShowStatus;
    private String menuShowNum;
    private String menuShowIcon;

    @JSONField(serialize = false)
    @Override
    public Object getIdValue() {
        return menuId;
    }
    @JSONField(serialize = false)
    @Override
    public Object getPidValue() {
        return parentId;
    }

    @Override
    public List<SysMenuTree> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<SysMenuTree> children) {
        this.children = children;
    }

    public static SysMenuTree buildTree(List<SysMenu> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        SysMenu parent = new SysMenu();
        parent.setMenuId("-1");
        return buildTree(list, parent);
    }

    public static SysMenuTree buildTree(List<SysMenu> list, SysMenu parent) {
        if (CollectionUtils.isEmpty(list)) return null;
        AbstractTreeBuilder<SysMenu, SysMenuTree> builder = new AbstractTreeBuilder<SysMenu, SysMenuTree>() {

            @Override
            public SysMenuTree convert(SysMenu obj) {
                SysMenuTree tree = new SysMenuTree();
                tree.setMenuId(obj.getMenuId());
                tree.setParentId(obj.getParentId());
                tree.setMenuName(obj.getMenuName());
                tree.setMenuShowStatus(obj.getMenuShowStatus());
                tree.setMenuShowIcon(obj.getMenuShowIcon());
                tree.setMenuShowNum(obj.getMenuShowNum());
                return tree;
            }
        };
        return builder.buildTree(parent, list);
    }

}
