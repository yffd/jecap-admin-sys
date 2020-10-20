package com.yffd.jecap.admin.sys.domain.menu.service;

import com.yffd.jecap.admin.base.dao.IBaseDao;
import com.yffd.jecap.admin.sys.domain.constant.SysConsts;
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

@Service
public class SysMenuService {
    @Autowired private ISysMenuRepo menuRepo;

    private IBaseDao<SysMenu> getDao() {
        return this.menuRepo.getMenuDao();
    }

    public void add(SysMenu menu) {
        if (null == menu || StringUtils.isBlank(menu.getMenuName())) throw SysException.cast("菜单名称不能为空").prompt();
        if (null != this.queryByMenuName(menu.getMenuName())) throw SysException.cast("菜单名称已存在").prompt();
        this.getDao().addBy(menu);
    }

    /**
     * 添加根节点
     * @param menu
     */
    public void addRoot(SysMenu menu) {
        if (null == menu || StringUtils.isBlank(menu.getMenuName())) throw SysException.cast("菜单名称不能为空").prompt();
        if (null != this.queryByMenuName(menu.getMenuName())) throw SysException.cast("菜单名称已存在").prompt();
        menu.setPid(SysConsts.DEF_TREE_ROOT_ID);
        this.getDao().addBy(menu);
    }

    /**
     * 添加子节点
     * @param menu
     */
    @Transactional
    public void addChild(SysMenu menu) {
        if (null == menu || StringUtils.isBlank(menu.getMenuName())) throw SysException.cast("菜单名称不能为空").prompt();
        if (null != this.queryByMenuName(menu.getMenuName())) throw SysException.cast("菜单名称已存在").prompt();
        SysMenu parent = this.getDao().queryById(menu.getPid());
        if (null == parent) throw SysException.cast("父ID不存在");
        if (StringUtils.isBlank(parent.getPath())) {
            menu.setPath(parent.getId());
        } else {
            menu.setPath(parent.getPath() + "," + parent.getId());
        }
        this.getDao().addBy(menu);
    }

    /**
     * 只修改当前节点的信息，不包括子节点
     * @param menu
     */
    public void updateById(SysMenu menu) {
        if (null == menu || StringUtils.isBlank(menu.getId())) return;
        menu.setPid(null);//父ID不可修改
        this.getDao().modifyById(menu);
    }

    /**
     * 修改当前节点信息，以及其子节点的path
     * @param menu
     * @param pid
     */
    @Transactional
    public void updateById(SysMenu menu, String pid) {
        if (null == menu || StringUtils.isBlank(menu.getId())) return;
        SysMenu curNode = this.getDao().queryById(menu.getId());
        if (null == curNode) return;
        if (StringUtils.isBlank(pid)) {
            this.getDao().modifyById(menu);//修改当前节点信息
        } else if (!pid.equals(curNode.getPid())) {//变更父节点
            SysMenu parent = this.getDao().queryById(pid);
            if (null == parent) throw SysException.cast("父ID不存在");
            String curPath = curNode.getPath();
            String newPath = StringUtils.isBlank(parent.getPath()) ? parent.getId() : parent.getPath() + "," + parent.getId();
            menu.setPath(newPath);
            menu.setPid(parent.getId());
            this.getDao().modifyById(menu);//修改当前节点信息

            //更改子节点path、pid
            List<SysMenu> children = this.queryByPath(curNode.getPath() + "," + curNode.getId());
            if (CollectionUtils.isNotEmpty(children)) {
                for (SysMenu child : children) {
                    if (null == child || StringUtils.isBlank(child.getPath())) continue;
                    String suffix = child.getPath().substring(curPath.length() + 1);
                    String tmp = newPath + (StringUtils.isBlank(suffix) ? "" : "," + suffix);
                    child.setPath(tmp);

                    String _pid = child.getPath().substring(child.getPath().lastIndexOf(",") + 1);
                    child.setPid(_pid);
                    this.getDao().modifyById(child);//修改当前节点信息
                }
            }
        }
    }

    @Transactional
    public void deleteById(String menuId) {
        if (null == menuId) return;
        SysMenu menu = this.queryById(menuId);
        if (null == menu) return;
        if (StringUtils.isNotBlank(menu.getPath())) {
            this.menuRepo.removeByPath(menu.getPath() + "," + menuId);
            this.getDao().removeById(menuId);
        } else {
            this.menuRepo.removeByPath(menuId);
            this.getDao().removeById(menuId);
        }
    }

    public List<SysMenu> queryChildren(String menuId) {
        if (StringUtils.isBlank(menuId)) return Collections.EMPTY_LIST;
        SysMenu entity = this.getDao().queryById(menuId);
        if (null == entity || StringUtils.isBlank(entity.getPath())) return Collections.EMPTY_LIST;
        return this.queryByPath(entity.getPath() + "," + entity.getId());
    }

    public SysMenu queryByMenuName(String menuName) {
        if (StringUtils.isBlank(menuName)) return null;
        SysMenu entity = new SysMenu();
        entity.setMenuName(menuName);
        return this.getDao().queryOne(entity);
    }

    public List<SysMenu> queryByPath(String path) {
        if (StringUtils.isBlank(path)) return Collections.EMPTY_LIST;
        SysMenu entity = new SysMenu();
        entity.setPath(path);
        return this.getDao().queryList(entity);
    }

    public SysMenu queryById(String menuId) {
        if (StringUtils.isBlank(menuId)) return null;
        return this.getDao().queryById(menuId);
    }

    public List<SysMenu> queryAll() {
        return this.getDao().queryAll();
    }

}
