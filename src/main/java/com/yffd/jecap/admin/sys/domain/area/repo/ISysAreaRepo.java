package com.yffd.jecap.admin.sys.domain.area.repo;

import com.yffd.jecap.admin.sys.domain.area.entity.SysArea;
import com.yffd.jecap.admin.sys.domain.area.entity.SysAreaAddress;
import com.yffd.jecap.admin.base.dao.IBaseDao;

import java.util.List;

public interface ISysAreaRepo {

    IBaseDao<SysArea> getAreaDao();
    IBaseDao<SysAreaAddress> getAreaAddressDao();

    void removeByPath(String pathPrefix);

    /**
     * 精准查询
     * @param areaName
     * @return
     */
    SysArea queryByAreaName(String areaName);
}
