package com.yffd.jecap.admin.sys.domain.menu.repo;

import com.yffd.jecap.admin.sys.domain.menu.entity.SysMenuPmsn;
import com.yffd.jecap.admin.sys.domain.menu.entity.SysMenu;
import com.yffd.jecap.admin.base.dao.IBaseDao;

public interface ISysMenuRepo {

    IBaseDao<SysMenu> getMenuDao();
    IBaseDao<SysMenuPmsn> getMenuPmsnDao();

    void removeByPath(String pathPrefix);
}
