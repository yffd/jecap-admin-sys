package com.yffd.jecap.admin.sys.infrastructure.repository;

import com.yffd.jecap.admin.base.dao.IBaseDao;
import com.yffd.jecap.admin.base.repository.AbstractBaseRepo;
import com.yffd.jecap.admin.sys.domain.role.entity.SysRole;
import com.yffd.jecap.admin.sys.domain.role.repo.ISysRoleRepo;
import com.yffd.jecap.admin.sys.infrastructure.dao.ISysRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SysRoleRepo extends AbstractBaseRepo<SysRole> implements ISysRoleRepo {
    @Autowired private ISysRoleDao roleDao;

    @Override
    protected IBaseDao<SysRole> getDao() {
        return roleDao;
    }

}
