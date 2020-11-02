package com.yffd.jecap.admin.sys.infrastructure.repository;

import com.yffd.jecap.admin.base.dao.IBaseDao;
import com.yffd.jecap.admin.base.repository.AbstractBaseRepo;
import com.yffd.jecap.admin.sys.domain.login.entity.SysLoginAcnt;
import com.yffd.jecap.admin.sys.domain.login.repo.ISysLoginAcntRepo;
import com.yffd.jecap.admin.sys.infrastructure.dao.ISysLoginAcntDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SysLoginAcntRepo extends AbstractBaseRepo<SysLoginAcnt> implements ISysLoginAcntRepo {
    @Autowired private ISysLoginAcntDao loginAcntDao;

    @Override
    protected IBaseDao<SysLoginAcnt> getDao() {
        return loginAcntDao;
    }
}
