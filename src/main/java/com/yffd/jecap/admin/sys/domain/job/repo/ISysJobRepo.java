package com.yffd.jecap.admin.sys.domain.job.repo;

import com.yffd.jecap.admin.sys.infrastructure.dao.ISysJobDao;
import com.yffd.jecap.admin.sys.infrastructure.dao.ISysJobOrgDao;

public interface ISysJobRepo {

    ISysJobDao getJobDao();
    ISysJobOrgDao getJobOrgDao();

}
