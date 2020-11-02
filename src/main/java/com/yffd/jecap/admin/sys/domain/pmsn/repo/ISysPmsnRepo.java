package com.yffd.jecap.admin.sys.domain.pmsn.repo;

import com.yffd.jecap.admin.base.repository.IBaseRepository;
import com.yffd.jecap.admin.sys.domain.pmsn.entity.SysPmsn;

import java.util.Set;

public interface ISysPmsnRepo extends IBaseRepository<SysPmsn> {

    void modifyBatchPmsnStatus(Set<String> pmsnIds, String pmsnStatus);
}
