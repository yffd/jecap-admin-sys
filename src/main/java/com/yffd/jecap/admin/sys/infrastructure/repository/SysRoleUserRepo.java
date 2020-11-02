package com.yffd.jecap.admin.sys.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yffd.jecap.admin.base.dao.IBaseDao;
import com.yffd.jecap.admin.base.repository.AbstractBaseRepo;
import com.yffd.jecap.admin.sys.domain.rlt.entity.SysRoleUser;
import com.yffd.jecap.admin.sys.domain.rlt.repo.ISysRoleUserRepo;
import com.yffd.jecap.admin.sys.infrastructure.dao.ISysRoleUserDao;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class SysRoleUserRepo extends AbstractBaseRepo<SysRoleUser> implements ISysRoleUserRepo {
    @Autowired
    private ISysRoleUserDao roleUserDao;

    @Override
    protected IBaseDao<SysRoleUser> getDao() {
        return roleUserDao;
    }

    @Override
    public void removeByUserId(String userId, Set<String> roleIds) {
        LambdaQueryWrapper<SysRoleUser> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysRoleUser::getUserId, userId);
        if (CollectionUtils.isNotEmpty(roleIds))
            wrapper.in(SysRoleUser::getRoleId, roleIds);
        this.roleUserDao.delete(wrapper);
    }

    @Override
    public void removeByRoleId(String roleId, Set<String> userIds) {
        LambdaQueryWrapper<SysRoleUser> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysRoleUser::getRoleId, roleId);
        if (CollectionUtils.isNotEmpty(userIds))
            wrapper.in(SysRoleUser::getUserId, userIds);
        this.roleUserDao.delete(wrapper);
    }

}
