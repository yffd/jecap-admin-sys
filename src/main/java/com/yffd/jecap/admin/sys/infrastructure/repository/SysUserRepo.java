package com.yffd.jecap.admin.sys.infrastructure.repository;

import com.yffd.jecap.admin.base.dao.IBaseDao;
import com.yffd.jecap.admin.base.repository.AbstractBaseRepo;
import com.yffd.jecap.admin.sys.domain.user.entity.SysUser;
import com.yffd.jecap.admin.sys.domain.user.repo.ISysUserRepo;
import com.yffd.jecap.admin.sys.infrastructure.dao.ISysUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class SysUserRepo extends AbstractBaseRepo<SysUser> implements ISysUserRepo {
    @Autowired
    private ISysUserDao userDao;

    @Override
    protected IBaseDao<SysUser> getDao() {
        return userDao;
    }

    @Override
    public Set<String> queryRltRoleCode(String userId) {
        return this.userDao.queryRltRoleCode(userId);
    }

    @Override
    public Set<String> queryRltPmsnCode(String userId) {
        return this.userDao.queryRltPmsnCode(userId);
    }
}
