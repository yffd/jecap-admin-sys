package com.yffd.jecap.admin.sys.domain.api.repo;

import com.yffd.jecap.admin.sys.domain.api.entity.SysApi;
import com.yffd.jecap.admin.sys.domain.api.entity.SysApiPmsn;
import com.yffd.jecap.admin.base.dao.IBaseDao;

public interface ISysApiRepo {

    IBaseDao<SysApi> getApiDao();
    IBaseDao<SysApiPmsn> getApiPmsDao();

}
