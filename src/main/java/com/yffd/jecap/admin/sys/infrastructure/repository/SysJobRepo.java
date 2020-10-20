package com.yffd.jecap.admin.sys.infrastructure.repository;

import com.yffd.jecap.admin.sys.domain.job.repo.ISysJobRepo;
import com.yffd.jecap.admin.sys.infrastructure.dao.job.ISysJobDao;
import com.yffd.jecap.admin.sys.infrastructure.dao.job.ISysJobOrgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SysJobRepo implements ISysJobRepo {
    @Autowired
    private ISysJobDao jobDao;
    @Autowired private ISysJobOrgDao jobOrgDao;

    @Override
    public ISysJobDao getJobDao() {
        return jobDao;
    }

    @Override
    public ISysJobOrgDao getJobOrgDao() {
        return jobOrgDao;
    }
}
