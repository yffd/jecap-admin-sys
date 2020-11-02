package com.yffd.jecap.admin.sys.application.service;

import com.yffd.jecap.admin.sys.application.model.menu.MenuSaveDto;
import com.yffd.jecap.admin.sys.application.model.menu.SysMenuTree;
import com.yffd.jecap.admin.sys.domain.exception.SysException;
import com.yffd.jecap.admin.sys.domain.menu.entity.SysMenu;
import com.yffd.jecap.admin.sys.domain.menu.service.SysMenuService;
import com.yffd.jecap.admin.sys.domain.pmsn.entity.SysPmsn;
import com.yffd.jecap.admin.sys.domain.pmsn.service.SysPmsnService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@Transactional
public class SysMenuAppService {
    @Autowired private SysMenuService menuService;
    @Autowired private SysPmsnService pmsnService;

    public List<SysMenuTree> queryTree() {
        List<SysMenu> list = this.menuService.queryAll();
        SysMenuTree tree = SysMenuTree.buildTree(list);
        if (null == tree) return Collections.emptyList();
        return tree.getChildren();
    }

    public void addBy(MenuSaveDto dto) {
        if (null == dto) throw SysException.cast("参数错误").prompt();
        //权限
        SysPmsn pmsn = dto.buildPmsn();
        if (null != pmsn) this.pmsnService.addBy(pmsn);
        //菜单
        SysMenu menu = dto.getMenu();
        if (null != pmsn) menu.setPmsnId(pmsn.getPmsnId());
        this.menuService.add(menu);
    }


    /**
     * 变更父节点
     * @param menuId
     * @param newParentId
     */
    public void modifyParentId(String menuId, String newParentId) {
        if (StringUtils.isAnyBlank(menuId, newParentId)) return;
        SysMenu curNode = this.menuService.queryById(menuId);
        if (null == curNode || newParentId.equals(curNode.getParentId())) return;

        SysMenu parentNode = this.menuService.queryById(newParentId);
        if (null == parentNode) return;
        String newParentPath = StringUtils.isBlank(parentNode.getParentPath()) ? parentNode.getMenuId() : parentNode.getParentPath() + "," + parentNode.getMenuId();
        //更改子节点path、pid
        String curParentPath = curNode.getParentPath();
        List<SysMenu> children = this.menuService.queryChildren(menuId);
        if (CollectionUtils.isNotEmpty(children)) {
            for (SysMenu child : children) {
                if (null == child || StringUtils.isBlank(child.getParentPath())) continue;
                String suffix = child.getParentPath().substring(curParentPath.length() + 1);
                String tmp = newParentPath + (StringUtils.isBlank(suffix) ? "" : "," + suffix);
                String pid = tmp.substring(tmp.lastIndexOf(",") + 1);
                SysMenu entity = new SysMenu();
                entity.setMenuId(child.getMenuId());
                entity.setParentPath(tmp);
                entity.setParentId(pid);
                this.menuService.modifyById(entity);//修改当前节点信息
            }
        }
        //修改当前节点信息
        SysMenu curMenu = new SysMenu();
        curMenu.setMenuId(menuId);
        curMenu.setParentPath(newParentPath);
        curMenu.setParentId(newParentId);
        this.menuService.modifyById(curMenu);

    }

    public void removeByIdWithChildren(String menuId) {
        if (StringUtils.isBlank(menuId)) return;
        SysMenu menu = this.menuService.queryById(menuId);
        if (null == menu) return;
        this.menuService.removeById(menuId);//删除当前节点
        this.menuService.removeChildren(menuId);//删除子节点
    }

    public void modifyById(MenuSaveDto params) {

    }

//    public void update(MenuSaveDto dto) {
//        if (null == dto) throw TokenInvalidException.cast("参数错误").prompt();
//        //菜单
//        SysMenu menu = dto.getMenu();
//        this.menuService.updateById(menu);
//        //权限
//        SysPmsn pmsn = dto.buildPmsn();
//        pmsn.setPmsnId(menu.getPmsnId());
//        if (null != pmsn) this.pmsnService.updateById(pmsn);
//    }
//
//    public void delete(String menuId) {
//        if (StringUtils.isBlank(menuId)) throw TokenInvalidException.cast("参数错误").prompt();
//        SysMenu menu = this.menuService.queryById(menuId);
//        if (null == menu) return;
//        this.menuService.deleteById(menuId);
//        if (StringUtils.isNotBlank(menu.getPmsnId()))
//        this.pmsnService.deleteById(menu.getPmsnId());//删除关联关系
//    }

}
