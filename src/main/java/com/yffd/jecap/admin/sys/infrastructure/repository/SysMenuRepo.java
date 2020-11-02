package com.yffd.jecap.admin.sys.infrastructure.repository;

import com.yffd.jecap.admin.base.dao.IBaseDao;
import com.yffd.jecap.admin.base.repository.AbstractBaseRepo;
import com.yffd.jecap.admin.sys.domain.menu.entity.SysMenu;
import com.yffd.jecap.admin.sys.domain.menu.repo.ISysMenuRepo;
import com.yffd.jecap.admin.sys.infrastructure.dao.ISysMenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SysMenuRepo extends AbstractBaseRepo<SysMenu> implements ISysMenuRepo {
    @Autowired
    private ISysMenuDao menuDao;

    @Override
    protected IBaseDao<SysMenu> getDao() {
        return menuDao;
    }

}
