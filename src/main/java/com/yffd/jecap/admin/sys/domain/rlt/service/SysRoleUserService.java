package com.yffd.jecap.admin.sys.domain.rlt.service;

import com.yffd.jecap.admin.sys.domain.rlt.entity.SysRoleUser;
import com.yffd.jecap.admin.sys.domain.rlt.repo.ISysRoleUserRepo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SysRoleUserService {
    @Autowired private ISysRoleUserRepo roleUserRepo;

    /**
     * 根据用户查询关联的用户
     * @param roleId
     * @return
     */
    public Set<String> queryUserIds(String roleId) {
        if (StringUtils.isBlank(roleId)) return Collections.emptySet();
        List<SysRoleUser> list = this.roleUserRepo.queryList(new SysRoleUser(roleId, null));
        if (CollectionUtils.isEmpty(list)) return Collections.emptySet();
        return list.stream().map(tmp -> tmp.getUserId()).collect(Collectors.toSet());
    }

    /**
     * 根据用户查询关联的角色
     * @param userId
     * @return
     */
    public Set<String> queryRoleIds(String userId) {
        if (StringUtils.isBlank(userId)) return Collections.emptySet();
        List<SysRoleUser> list = this.roleUserRepo.queryList(new SysRoleUser(null, userId));
        if (CollectionUtils.isEmpty(list)) return Collections.emptySet();
        return list.stream().map(tmp -> tmp.getRoleId()).collect(Collectors.toSet());
    }

    /**
     * 判断关联关系是否已存在
     * @param userId
     * @param roleId
     * @return
     */
    public boolean existBy(String userId, String roleId) {
        if (StringUtils.isAnyBlank(userId, roleId)) return false;
        return null != this.roleUserRepo.queryOne(new SysRoleUser(roleId, userId));
    }

    /**
     * 添加一个关联关系
     * @param userId
     * @param roleId
     */
    public void addBy(String userId, String roleId) {
        if (StringUtils.isAnyBlank(userId, roleId)) return;
        if (this.existBy(userId, roleId)) return;
        this.roleUserRepo.addBy(new SysRoleUser(roleId, userId));
    }

    /**
     * 删除一个关联关系
     * @param userId
     * @param roleId
     */
    public void removeBy(String userId, String roleId) {
        if (StringUtils.isAnyBlank(userId, roleId)) return;
        this.roleUserRepo.removeBy(new SysRoleUser(roleId, userId));
    }

    /**
     * 通过用户方向，删除关联关系
     * @param userId
     * @param roleIds   如果为空，则删除所有关联的角色
     */
    public void removeByUserId(String userId, Set<String> roleIds) {
        if (StringUtils.isBlank(userId)) return;
        this.roleUserRepo.removeByUserId(userId, roleIds);
    }

    /**
     * 通过角色方向，删除关联关系
     * @param roleId
     * @param userIds   如果为空，则删除所有关联的用户
     */
    public void removeByRoleId(String roleId, Set<String> userIds) {
        if (StringUtils.isBlank(roleId)) return;
        this.roleUserRepo.removeByRoleId(roleId, userIds);
    }


}
