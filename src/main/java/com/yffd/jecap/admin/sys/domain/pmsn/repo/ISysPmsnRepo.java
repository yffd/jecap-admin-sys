package com.yffd.jecap.admin.sys.domain.pmsn.repo;

import com.yffd.jecap.admin.sys.domain.pmsn.entity.SysPmsn;
import com.yffd.jecap.admin.base.dao.IBaseDao;

public interface ISysPmsnRepo {

    IBaseDao<SysPmsn> getPmsnDao();

}
