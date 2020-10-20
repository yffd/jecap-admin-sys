package com.yffd.jecap.admin.sys.domain.job.repo;

import com.yffd.jecap.admin.sys.infrastructure.dao.job.ISysJobDao;
import com.yffd.jecap.admin.sys.infrastructure.dao.job.ISysJobOrgDao;

public interface ISysJobRepo {

    ISysJobDao getJobDao();
    ISysJobOrgDao getJobOrgDao();

}
