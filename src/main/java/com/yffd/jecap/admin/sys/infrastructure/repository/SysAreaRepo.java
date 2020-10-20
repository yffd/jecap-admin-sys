package com.yffd.jecap.admin.sys.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yffd.jecap.admin.base.dao.IBaseDao;
import com.yffd.jecap.admin.sys.domain.area.entity.SysArea;
import com.yffd.jecap.admin.sys.domain.area.entity.SysAreaAddress;
import com.yffd.jecap.admin.sys.domain.area.repo.ISysAreaRepo;
import com.yffd.jecap.admin.sys.infrastructure.dao.area.ISysAreaAddressDao;
import com.yffd.jecap.admin.sys.infrastructure.dao.area.ISysAreaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SysAreaRepo implements ISysAreaRepo {
    @Autowired
    private ISysAreaDao areaDao;
    @Autowired private ISysAreaAddressDao areaAddressDao;

    @Override
    public IBaseDao<SysArea> getAreaDao() {
        return areaDao;
    }

    @Override
    public IBaseDao<SysAreaAddress> getAreaAddressDao() {
        return areaAddressDao;
    }

    @Override
    public void removeByPath(String pathPrefix) {
        this.areaDao.delete(new QueryWrapper<SysArea>().likeRight("path", pathPrefix));
    }

    @Override
    public SysArea queryByAreaName(String areaName) {
        return this.areaDao.selectOne(new QueryWrapper<SysArea>().eq("area_name", areaName));
    }

}
