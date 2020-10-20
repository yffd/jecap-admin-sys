package com.yffd.jecap.admin.sys.infrastructure.repository;

import com.yffd.jecap.admin.sys.domain.pmsn.entity.SysPmsn;
import com.yffd.jecap.admin.sys.domain.pmsn.repo.ISysPmsnRepo;
import com.yffd.jecap.admin.sys.infrastructure.dao.pmsn.ISysPmsnDao;
import com.yffd.jecap.admin.base.dao.IBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SysPmsnRepo implements ISysPmsnRepo {
    @Autowired
    private ISysPmsnDao pmsnDao;

    @Override
    public IBaseDao<SysPmsn> getPmsnDao() {
        return pmsnDao;
    }
}
