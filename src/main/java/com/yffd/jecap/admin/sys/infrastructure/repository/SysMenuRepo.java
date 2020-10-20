package com.yffd.jecap.admin.sys.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yffd.jecap.admin.base.dao.IBaseDao;
import com.yffd.jecap.admin.sys.domain.menu.entity.SysMenu;
import com.yffd.jecap.admin.sys.domain.menu.entity.SysMenuPmsn;
import com.yffd.jecap.admin.sys.domain.menu.repo.ISysMenuRepo;
import com.yffd.jecap.admin.sys.infrastructure.dao.menu.ISysMenuDao;
import com.yffd.jecap.admin.sys.infrastructure.dao.menu.ISysMenuPmsnDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SysMenuRepo implements ISysMenuRepo {
    @Autowired
    private ISysMenuDao menuDao;
    @Autowired private ISysMenuPmsnDao menuPmsnDao;

    @Override
    public IBaseDao<SysMenu> getMenuDao() {
        return menuDao;
    }

    @Override
    public IBaseDao<SysMenuPmsn> getMenuPmsnDao() {
        return menuPmsnDao;
    }

    @Override
    public void removeByPath(String pathPrefix) {
        this.menuDao.delete(new QueryWrapper<SysMenu>().likeRight("path", pathPrefix));
    }
}
