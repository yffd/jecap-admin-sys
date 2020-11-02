package com.yffd.jecap.admin.sys.domain.menu.service;

import com.yffd.jecap.admin.sys.domain.exception.SysException;
import com.yffd.jecap.admin.sys.domain.menu.entity.SysMenu;
import com.yffd.jecap.admin.sys.domain.menu.repo.ISysMenuRepo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class SysMenuService {
    @Autowired private ISysMenuRepo menuRepo;

    public String add(SysMenu menu) {
        if (null == menu || StringUtils.isBlank(menu.getMenuName())) throw SysException.cast("菜单名称不能为空").prompt();
        if (null != this.queryByMenuName(menu.getMenuName())) throw SysException.cast("菜单名称已存在").prompt();
        if (StringUtils.isBlank(menu.getParentId())) {
            return this.addRoot(menu);
        } else {
            return this.addChild(menu);
        }
    }

    /**
     * 添加根节点
     * @param menu
     */
    public String addRoot(SysMenu menu) {
        if (null == menu || StringUtils.isBlank(menu.getMenuName())) throw SysException.cast("【菜单名称】不能为空").prompt();
        if (null != this.queryByMenuName(menu.getMenuName())) throw SysException.cast("【菜单名称】已存在").prompt();
        menu.setParentId("-1");
        this.menuRepo.addBy(menu);
        return menu.getMenuId();
    }

    /**
     * 添加子节点
     * @param menu
     */
    @Transactional
    public String addChild(SysMenu menu) {
        if (null == menu || StringUtils.isBlank(menu.getMenuName())) throw SysException.cast("【菜单名称】不能为空").prompt();
        if (null != this.queryByMenuName(menu.getMenuName())) throw SysException.cast("【菜单名称】已存在").prompt();
        SysMenu parent = this.menuRepo.queryById(menu.getParentId());
        if (null == parent) throw SysException.cast("父ID已不存在").prompt();
        String parentPath = parent.getParentPath();
        if (StringUtils.isBlank(parentPath)) {
            menu.setParentPath(parent.getMenuId());
        } else {
            menu.setParentPath(parentPath + "," + parent.getMenuId());
        }
        this.menuRepo.addBy(menu);
        return menu.getMenuId();
    }

    public void modifyById(SysMenu menu) {
        if (null == menu || StringUtils.isBlank(menu.getMenuId())) return;
        this.menuRepo.modifyById(menu);
    }

    public void removeById(String menuId) {
        if (null == menuId) return;
        this.menuRepo.removeById(menuId);
    }

    public void removeBatchById(Set<String> menuIds) {
        if (CollectionUtils.isEmpty(menuIds)) return;
        this.menuRepo.removeByIds(menuIds);
    }

    public void removeChildren(String menuId) {
        if (StringUtils.isBlank(menuId)) return;
        SysMenu menu = this.menuRepo.queryById(menuId);
        if (null == menu) return;
        String parentPath = menu.getParentPath();
        if (StringUtils.isBlank(parentPath)) return;
        SysMenu entity = new SysMenu();
        entity.setParentPath(parentPath + "," + menuId);
        this.menuRepo.removeBy(entity);
    }

    public List<SysMenu> queryChildren(String menuId) {
        if (StringUtils.isBlank(menuId)) return Collections.EMPTY_LIST;
        SysMenu entity = this.menuRepo.queryById(menuId);
        if (null == entity || StringUtils.isBlank(entity.getParentPath())) return Collections.EMPTY_LIST;
        return this.queryByPath(entity.getParentPath() + "," + entity.getMenuId());
    }

    public SysMenu queryByMenuName(String menuName) {
        if (StringUtils.isBlank(menuName)) return null;
        SysMenu entity = new SysMenu();
        entity.setMenuName(menuName);
        return this.menuRepo.queryOne(entity);
    }

    public List<SysMenu> queryByPath(String path) {
        if (StringUtils.isBlank(path)) return Collections.EMPTY_LIST;
        SysMenu entity = new SysMenu();
        entity.setParentPath(path);
        return this.menuRepo.queryList(entity);
    }

    public SysMenu queryById(String menuId) {
        if (StringUtils.isBlank(menuId)) return null;
        return this.menuRepo.queryById(menuId);
    }

    public List<SysMenu> queryAll() {
        return this.menuRepo.queryAll();
    }


}
