package com.yffd.jecap.admin.sys.application.service;

import com.yffd.jecap.admin.sys.domain.rlt.service.SysRolePmsnService;
import com.yffd.jecap.admin.sys.domain.rlt.service.SysRoleUserService;
import com.yffd.jecap.admin.sys.domain.role.entity.SysRole;
import com.yffd.jecap.admin.sys.domain.role.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Service
@Transactional
public class SysRoleAppService {
    @Autowired private SysRoleService roleService;
    @Autowired private SysRoleUserService roleUserService;
    @Autowired private SysRolePmsnService rolePmsnService;

    /**
     * 添加角色，同时关联用户集
     * @param role
     * @param userIds   可为空
     */
    public void addWithUser(SysRole role, Set<String> userIds) {
        if (null == role) return;
        this.roleService.addBy(role);
        // 关联用户
        if (CollectionUtils.isNotEmpty(userIds))
            userIds.forEach(userId -> this.roleUserService.addBy(userId, role.getRoleId()));//添加关联-用户
    }

    /**
     * 添加角色，同时关联权限集
     * @param role
     * @param pmsnIds   可为空
     */
    public void addWithPmsn(SysRole role, Set<String> pmsnIds) {
        if (null == role) return;
        this.roleService.addBy(role);
        // 关联权限
        if (CollectionUtils.isNotEmpty(pmsnIds))
            pmsnIds.forEach(pmsnId -> this.rolePmsnService.addBy(pmsnId, role.getRoleId()));//添加关联-权限
    }

    /**
     * 修改角色关联的用户集
     * @param roleId
     * @param addUserIds
     * @param removeUserIds
     */
    public void modifyRltUser(String roleId, Set<String> addUserIds, Set<String> removeUserIds) {
        if (StringUtils.isBlank(roleId)) return;
        if (CollectionUtils.isNotEmpty(removeUserIds))
            this.roleUserService.removeByRoleId(roleId, removeUserIds);//删除关联-用户
        if (CollectionUtils.isNotEmpty(addUserIds))
            addUserIds.forEach(userId -> this.roleUserService.addBy(userId, roleId));//添加关联-用户
    }

    /**
     * 修改角色关联的权限集
     * @param roleId
     * @param addPmsnIds
     * @param removePmsnIds
     */
    public void modifyRltPmsn(String roleId, Set<String> addPmsnIds, Set<String> removePmsnIds) {
        if (StringUtils.isBlank(roleId)) return;
        if (CollectionUtils.isNotEmpty(removePmsnIds))
            this.rolePmsnService.removeByRoleId(roleId, removePmsnIds);//删除关联-权限
        if (CollectionUtils.isNotEmpty(addPmsnIds))
            addPmsnIds.forEach(pmsnId -> this.rolePmsnService.addBy(pmsnId, roleId));//添加关联-权限
    }

    /**
     * 删除角色，同时删除关联集
     * @param roleId
     */
    public void removeById(String roleId) {
        if (StringUtils.isBlank(roleId)) return;
        this.roleService.removeById(roleId);
        this.roleUserService.removeByRoleId(roleId, null);//删除关联-用户
        this.rolePmsnService.removeByRoleId(roleId, null);//删除关联-权限
    }
}
