package com.yffd.jecap.admin.sys.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yffd.jecap.admin.sys.domain.user.entity.SysUserGroup;
import com.yffd.jecap.admin.sys.domain.user.entity.SysUserJob;
import com.yffd.jecap.admin.sys.domain.user.repo.ISysUserRepo;
import com.yffd.jecap.admin.sys.infrastructure.dao.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class SysUserRepo implements ISysUserRepo {
    @Autowired
    private ISysUserDao userDao;
    @Autowired private ISysUserGroupDao userGroupDao;
    @Autowired private ISysUserRoleDao userRoleDao;
    @Autowired private ISysUserJobDao userJobDao;
    @Autowired private ISysUserLoginDao userLoginDao;

    @Override
    public ISysUserDao getUserDao() {
        return userDao;
    }

    @Override
    public ISysUserGroupDao getUserGroupDao() {
        return userGroupDao;
    }

    @Override
    public ISysUserRoleDao getUserRoleDao() {
        return userRoleDao;
    }

    @Override
    public ISysUserJobDao getUserJobDao() {
        return userJobDao;
    }

    @Override
    public ISysUserLoginDao getUserLoginDao() {
        return userLoginDao;
    }

    @Override
    public void removeRole(String userId, Set<String> roleIds) {
        Set<String> userIds = new HashSet<>();
        userIds.add(userId);
        this.getUserRoleDao().removeByIn(userIds, roleIds);
    }

    @Override
    public void removeGroup(String userId, Set<String> groupIds) {
        this.getUserGroupDao().delete(new QueryWrapper<SysUserGroup>()
                .eq("user_id", userId)
                .in("group_id", groupIds));
    }

    @Override
    public void removeJob(String userId, Set<String> jobIds) {
        this.getUserJobDao().delete(new QueryWrapper<SysUserJob>()
                .eq("user_id", userId)
                .in("job_id", jobIds));
    }

}
