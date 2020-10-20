package com.yffd.jecap.admin.sys.domain.user.service;

import com.yffd.jecap.admin.sys.domain.user.entity.SysUserRole;
import com.yffd.jecap.admin.sys.domain.user.repo.ISysUserRepo;
import com.yffd.jecap.admin.sys.infrastructure.dao.user.ISysUserRoleDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class SysUserRoleService {
    @Autowired
    private ISysUserRepo userRepo;

    private ISysUserRoleDao getDao() {
        return this.userRepo.getUserRoleDao();
    }

    /**
     * 添加关系
     * @param userId
     * @param roleId
     */
    public void addRlt(String userId, String roleId) {
        if (StringUtils.isAnyBlank(userId, roleId)) return;
        SysUserRole entity = new SysUserRole(userId, roleId);
        if (null != this.getDao().queryOne(entity)) return;//已分配
        this.getDao().addBy(entity);
    }

    /**
     * 添加关系
     * @param userId
     * @param roleIds
     */
    @Transactional
    public void addRlt(String userId, Set<String> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) return;
        roleIds.forEach(roleId -> this.addRlt(userId, roleId));
    }

    /**
     * 添加关系
     * @param userIds
     * @param roleId
     */
    @Transactional
    public void addRlt(Set<String> userIds, String roleId) {
        if (CollectionUtils.isEmpty(userIds)) return;
        userIds.forEach(userId -> this.addRlt(userId, roleId));
    }

    /**
     * 添加或解除关系
     * @param userId
     * @param addRoleIds
     * @param delRoleIds
     */
    @Transactional
    public void updateRlt(String userId, Set<String> addRoleIds, Set<String> delRoleIds) {
        if (StringUtils.isBlank(userId)) return;
        if (CollectionUtils.isNotEmpty(delRoleIds)) delRoleIds.forEach(roleId -> this.deleteBy(userId, roleId));
        if (CollectionUtils.isNotEmpty(addRoleIds)) addRoleIds.forEach(roleId -> this.addRlt(userId, roleId));
    }

    /**
     * 删除关系
     * @param userId
     * @param roleId
     */
    public void deleteBy(String userId, String roleId) {
        if (StringUtils.isAnyBlank(userId, roleId)) return;
        this.getDao().removeBy(new SysUserRole(userId, roleId));
    }

    /**
     * 删除关系
     * @param userId
     * @param roleIds
     */
    public void deleteBy(String userId, Set<String> roleIds) {
        if (StringUtils.isBlank(userId) || CollectionUtils.isEmpty(roleIds)) return;
        this.userRepo.removeRole(userId, roleIds);
    }

    /**
     * 删除关系
     * @param roleId
     */
    public void deleteByRoleId(String roleId) {
        if (StringUtils.isBlank(roleId)) return;
        this.getDao().removeBy(new SysUserRole(null, roleId));
    }

    /**
     * 删除关系
     * @param userId
     */
    public void deleteByUserId(String userId) {
        if (StringUtils.isBlank(userId)) return;
        this.getDao().removeBy(new SysUserRole(userId, null));
    }

    /**
     * 查找关系
     * @param userId
     * @return
     */
    public Set<String> queryRoleIds(String userId) {
        if (StringUtils.isBlank(userId)) return Collections.emptySet();
        SysUserRole entity = new SysUserRole(userId, null);
        List<SysUserRole> list = this.getDao().queryList(entity);
        if (CollectionUtils.isEmpty(list)) return Collections.emptySet();
        Set<String> ids = new HashSet<>();
        list.forEach(tmp -> ids.add(tmp.getRoleId()));
        return ids;
    }

    /**
     * 查找关系
     * @param roleId
     * @return
     */
    public Set<String> queryUserIds(String roleId) {
        if (StringUtils.isBlank(roleId)) return Collections.emptySet();
        SysUserRole entity = new SysUserRole(null, roleId);
        List<SysUserRole> list = this.getDao().queryList(entity);
        if (CollectionUtils.isEmpty(list)) return Collections.emptySet();
        Set<String> ids = new HashSet<>();
        list.forEach(tmp -> ids.add(tmp.getUserId()));
        return ids;
    }

}
