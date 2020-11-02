package com.yffd.jecap.admin.sys.domain.rlt.repo;

import com.yffd.jecap.admin.base.repository.IBaseRepository;
import com.yffd.jecap.admin.sys.domain.rlt.entity.SysRolePmsn;

import java.util.Set;

public interface ISysRolePmsnRepo extends IBaseRepository<SysRolePmsn> {

    void removeByPmsnId(String pmsnId, Set<String> roleIds);

    void removeByRoleId(String roleId, Set<String> userIds);
}
