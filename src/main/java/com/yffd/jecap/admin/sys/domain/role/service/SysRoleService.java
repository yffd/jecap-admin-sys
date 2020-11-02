package com.yffd.jecap.admin.sys.domain.role.service;

import com.yffd.jecap.admin.base.page.PageData;
import com.yffd.jecap.admin.sys.domain.exception.SysException;
import com.yffd.jecap.admin.sys.domain.role.entity.SysRole;
import com.yffd.jecap.admin.sys.domain.role.repo.ISysRoleRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleService {
    @Autowired private ISysRoleRepo roleRepo;

    public String addBy(SysRole role) {
        if (null == role || StringUtils.isBlank(role.getRoleName()))
            throw SysException.cast("【角色名称】不能为空").prompt();
        if (StringUtils.isNotBlank(role.getRoleCode())
                && null != this.queryByRoleCode(role.getRoleCode())) {
            throw SysException.cast(String.format("角色编号已存在【%s】", role.getRoleCode())).prompt();
        }
        this.roleRepo.addBy(role.initValue());
        return role.getRoleId();
    }

    public void modifyById(SysRole role) {
        if (null == role || StringUtils.isBlank(role.getRoleId())) return;
        if (StringUtils.isNotBlank(role.getRoleCode())) {
            SysRole entity = this.queryByRoleCode(role.getRoleCode());
            if (null != entity && !entity.getRoleId().equals(role.getRoleId())) {
                throw SysException.cast(String.format("角色编号已存在【%s】", role.getRoleCode())).prompt();
            }
        }
        this.roleRepo.modifyById(role);
    }

    public void enableStatus(String roleId) {
        this.modifyStatus(roleId, "1");
    }

    public void disableStatus(String roleId) {
        this.modifyStatus(roleId, "0");
    }

    public void modifyStatus(String roleId, String roleStatus) {
        if (StringUtils.isAnyBlank(roleId, roleStatus)) return;
        SysRole entity = new SysRole();
        entity.setRoleId(roleId);
        entity.setRoleStatus(roleStatus);
        this.roleRepo.modifyById(entity);
    }

    public void removeById(String roleId) {
        if (StringUtils.isBlank(roleId)) return;
        this.roleRepo.removeById(roleId);
    }

    public SysRole queryById(String roleId) {
        if (StringUtils.isBlank(roleId)) return null;
        return this.roleRepo.queryById(roleId);
    }

    public PageData<SysRole> queryPage(SysRole role, int pageNum, int pageSize) {
        return this.roleRepo.queryPage(role, pageNum, pageSize);
    }

    public SysRole queryByRoleCode(String roleCode) {
        if (StringUtils.isBlank(roleCode)) return null;
        SysRole entity = new SysRole();
        entity.setRoleCode(roleCode);
        return this.roleRepo.queryOne(entity);
    }

}
