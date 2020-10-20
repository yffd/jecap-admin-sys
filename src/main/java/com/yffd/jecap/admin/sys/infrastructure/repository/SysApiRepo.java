package com.yffd.jecap.admin.sys.infrastructure.repository;

import com.yffd.jecap.admin.sys.domain.api.entity.SysApi;
import com.yffd.jecap.admin.sys.domain.api.entity.SysApiPmsn;
import com.yffd.jecap.admin.sys.domain.api.repo.ISysApiRepo;
import com.yffd.jecap.admin.sys.infrastructure.dao.api.ISysApiDao;
import com.yffd.jecap.admin.sys.infrastructure.dao.api.ISysApiPmsnDao;
import com.yffd.jecap.admin.base.dao.IBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SysApiRepo implements ISysApiRepo {
    @Autowired
    private ISysApiDao apiDao;
    @Autowired private ISysApiPmsnDao apiPmsnDao;

    @Override
    public IBaseDao<SysApi> getApiDao() {
        return apiDao;
    }

    @Override
    public IBaseDao<SysApiPmsn> getApiPmsDao() {
        return apiPmsnDao;
    }

}
