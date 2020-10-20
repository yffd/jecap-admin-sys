package com.yffd.jecap.admin.sys.infrastructure.repository;

import com.yffd.jecap.admin.sys.domain.role.entity.SysRolePmsn;
import com.yffd.jecap.admin.sys.domain.role.repo.ISysRoleRepo;
import com.yffd.jecap.admin.sys.infrastructure.dao.role.ISysRoleDao;
import com.yffd.jecap.admin.sys.infrastructure.dao.role.ISysRoleGroupDao;
import com.yffd.jecap.admin.sys.infrastructure.dao.role.ISysRolePmsnDao;
import com.yffd.jecap.admin.base.dao.IBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class SysRoleRepo implements ISysRoleRepo {
    @Autowired private ISysRoleDao roleDao;
    @Autowired private ISysRoleGroupDao roleGroupDao;
    @Autowired private ISysRolePmsnDao rolePmsnDao;

    @Override
    public ISysRoleDao getRoleDao() {
        return roleDao;
    }

    @Override
    public ISysRoleGroupDao getRoleGroupDao() {
        return roleGroupDao;
    }

    @Override
    public IBaseDao<SysRolePmsn> getRolePmsnDao() {
        return rolePmsnDao;
    }

    @Override
    public void removeGroup(String roleId, Set<String> groupIds) {

    }

    @Override
    public void removePmsn(String roleId, Set<String> pmsnIds) {

    }
}
