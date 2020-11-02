package com.yffd.jecap.admin.sys.domain.user.repo;

import com.yffd.jecap.admin.base.repository.IBaseRepository;
import com.yffd.jecap.admin.sys.domain.user.entity.SysUser;

import java.util.Set;

public interface ISysUserRepo extends IBaseRepository<SysUser> {

    Set<String> queryRltRoleCode(String userId);

    Set<String> queryRltPmsnCode(String userId);
}
