package com.yffd.jecap.admin.sys.infrastructure.repository;

import com.yffd.jecap.admin.base.dao.IBaseDao;
import com.yffd.jecap.admin.base.repository.AbstractBaseRepo;
import com.yffd.jecap.admin.sys.domain.pmsn.entity.SysPmsn;
import com.yffd.jecap.admin.sys.domain.pmsn.repo.ISysPmsnRepo;
import com.yffd.jecap.admin.sys.infrastructure.dao.ISysPmsnDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class SysPmsnRepo extends AbstractBaseRepo<SysPmsn> implements ISysPmsnRepo {
    @Autowired private ISysPmsnDao pmsnDao;

    @Override
    protected IBaseDao<SysPmsn> getDao() {
        return pmsnDao;
    }

    @Override
    public void modifyBatchPmsnStatus(Set<String> pmsnIds, String pmsnStatus) {
        this.pmsnDao.modifyBatchPmsnStatus(pmsnIds, pmsnStatus);
    }
}
