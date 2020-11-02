package com.yffd.jecap.admin.sys.infrastructure.repository;

import com.yffd.jecap.admin.base.dao.IBaseDao;
import com.yffd.jecap.admin.base.repository.AbstractBaseRepo;
import com.yffd.jecap.admin.sys.domain.login.entity.SysLoginInfo;
import com.yffd.jecap.admin.sys.domain.login.repo.ISysLoginInfoRepo;
import com.yffd.jecap.admin.sys.infrastructure.dao.ISysLoginInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SysLoginInfoRepo extends AbstractBaseRepo<SysLoginInfo> implements ISysLoginInfoRepo {
    @Autowired private ISysLoginInfoDao loginInfoDao;

    @Override
    protected IBaseDao<SysLoginInfo> getDao() {
        return loginInfoDao;
    }
}
