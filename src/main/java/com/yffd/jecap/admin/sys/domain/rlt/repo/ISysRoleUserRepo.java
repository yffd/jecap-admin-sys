package com.yffd.jecap.admin.sys.domain.rlt.repo;

import com.yffd.jecap.admin.base.repository.IBaseRepository;
import com.yffd.jecap.admin.sys.domain.rlt.entity.SysRoleUser;

import java.util.Set;

public interface ISysRoleUserRepo extends IBaseRepository<SysRoleUser> {

    void removeByUserId(String userId, Set<String> roleIds);

    void removeByRoleId(String roleId, Set<String> userIds);
}
