package com.yffd.jecap.admin.sys.application.service;

import com.yffd.jecap.admin.sys.application.dto.user.UserModifyDTO;
import com.yffd.jecap.admin.sys.application.dto.user.UserSaveDTO;
import com.yffd.jecap.admin.sys.domain.login.entity.SysLoginAcnt;
import com.yffd.jecap.admin.sys.domain.login.service.SysLoginAcntService;
import com.yffd.jecap.admin.sys.domain.rlt.service.SysRoleUserService;
import com.yffd.jecap.admin.sys.domain.user.entity.SysUser;
import com.yffd.jecap.admin.sys.domain.user.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;

@Slf4j
@Service
@Transactional
public class SysUserAppService {
    @Autowired private SysUserService userService;
    @Autowired private SysLoginAcntService loginAcntService;
    @Autowired private SysRoleUserService roleUserService;

    /**
     * 添加用户
     * @param dto
     */
    public void addBy(UserSaveDTO dto) {
        if (null == dto) return;
        SysUser user = dto.getUser();
        if (null == user) return;
        //开通登录账户
        if (!StringUtils.isAnyBlank(dto.getLoginName(), dto.getLoginPwd())) {
            String loginAcntId = this.loginAcntService.addBy(new SysLoginAcnt(dto.getLoginName(), dto.getLoginPwd()));
            user.setLoginAcntId(loginAcntId);//添加关联-账户
        }
        //添加用户
        this.userService.addBy(user);
        //添加关联-角色
        Set<String> roleIds = dto.getRoleIds();
        if (CollectionUtils.isEmpty(roleIds)) return;
        roleIds.forEach(roleId -> this.roleUserService.addBy(user.getUserId(), roleId));
    }

    /**
     * 修改
     * @param dto
     */
    public void modifyById(UserModifyDTO dto) {
        if (null == dto) return;
        String userId = dto.getUserId();
        if (StringUtils.isBlank(userId)) return;
        Set<String> addRoleIds = dto.getAddRoleIds();
        Set<String> removeRoleIds = dto.getRemoveRoleIds();
        if (CollectionUtils.isNotEmpty(removeRoleIds))
            this.roleUserService.removeByUserId(userId, removeRoleIds);//删除关联-角色
        if (CollectionUtils.isNotEmpty(addRoleIds)) {
            addRoleIds.forEach(roleId -> this.roleUserService.addBy(userId, roleId));//添加关联-角色
        }
    }

    /**
     * 为已存用户，开通登录账户
     * @param userId
     * @param loginAcntName
     * @param loginAcntPwd
     */
    public void addLoginAcnt(String userId, String loginAcntName, String loginAcntPwd) {
        if (StringUtils.isAnyBlank(userId, loginAcntName, loginAcntPwd)) return;
        String loginAcntId = this.loginAcntService.addBy(new SysLoginAcnt(loginAcntName, loginAcntPwd));
        SysUser entity = new SysUser();
        entity.setUserId(userId);
        entity.setLoginAcntId(loginAcntId);
        this.userService.modifyById(entity);
    }

    /**
     * 删除用户，同时删除关联集
     * @param userId
     */
    public void removeById(String userId) {
        if (StringUtils.isBlank(userId)) return;
        this.userService.removeById(userId);
        this.roleUserService.removeByUserId(userId, null);//删除关联-角色
    }

    public Set<String> queryRltRoleCode(String userId) {
        if (StringUtils.isBlank(userId)) Collections.emptyList();
        return this.userService.queryRltRoleCode(userId);
    }

    public Set<String> queryRltPmsnCode(String userId) {
        if (StringUtils.isBlank(userId)) Collections.emptyList();
        return this.userService.queryRltPmsnCode(userId);
    }

}
