package com.yffd.jecap.admin.sys.domain.org.repo;

import com.yffd.jecap.admin.sys.infrastructure.dao.ISysOrgDao;

public interface ISysOrgRepo {

    ISysOrgDao getOrgDao();

    void removeByPath(String pathPrefix);
}
