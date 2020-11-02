package com.yffd.jecap.admin.sys.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yffd.jecap.admin.base.dao.IBaseDao;
import com.yffd.jecap.admin.base.repository.AbstractBaseRepo;
import com.yffd.jecap.admin.sys.domain.rlt.entity.SysRolePmsn;
import com.yffd.jecap.admin.sys.domain.rlt.repo.ISysRolePmsnRepo;
import com.yffd.jecap.admin.sys.infrastructure.dao.ISysRolePmsnDao;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class SysRolePmsnRepo extends AbstractBaseRepo<SysRolePmsn> implements ISysRolePmsnRepo {
    @Autowired
    private ISysRolePmsnDao rolePmsnDao;

    @Override
    protected IBaseDao<SysRolePmsn> getDao() {
        return rolePmsnDao;
    }

    @Override
    public void removeByPmsnId(String pmsnId, Set<String> roleIds) {
        LambdaQueryWrapper<SysRolePmsn> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysRolePmsn::getPmsnId, pmsnId);
        if (CollectionUtils.isNotEmpty(roleIds))
            wrapper.in(SysRolePmsn::getRoleId, roleIds);
        this.rolePmsnDao.delete(wrapper);
    }

    @Override
    public void removeByRoleId(String roleId, Set<String> pmsnIds) {
        LambdaQueryWrapper<SysRolePmsn> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysRolePmsn::getRoleId, roleId);
        if (CollectionUtils.isNotEmpty(pmsnIds))
            wrapper.in(SysRolePmsn::getPmsnId, pmsnIds);
        this.rolePmsnDao.delete(wrapper);
    }

}
