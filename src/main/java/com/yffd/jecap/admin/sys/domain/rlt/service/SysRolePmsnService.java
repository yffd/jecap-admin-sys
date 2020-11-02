package com.yffd.jecap.admin.sys.domain.rlt.service;

import com.yffd.jecap.admin.sys.domain.rlt.entity.SysRolePmsn;
import com.yffd.jecap.admin.sys.domain.rlt.repo.ISysRolePmsnRepo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SysRolePmsnService {
    @Autowired
    private ISysRolePmsnRepo rolePmsnRepo;

    /**
     * 根据用户查询关联的权限
     * @param roleId
     * @return
     */
    public Set<String> queryPmsnIds(String roleId) {
        if (StringUtils.isBlank(roleId)) return Collections.emptySet();
        List<SysRolePmsn> list = this.rolePmsnRepo.queryList(new SysRolePmsn(roleId, null));
        if (CollectionUtils.isEmpty(list)) return Collections.emptySet();
        return list.stream().map(tmp -> tmp.getPmsnId()).collect(Collectors.toSet());
    }

    /**
     * 根据用户查询关联的角色
     * @param userId
     * @return
     */
    public Set<String> queryRoleIds(String userId) {
        if (StringUtils.isBlank(userId)) return Collections.emptySet();
        List<SysRolePmsn> list = this.rolePmsnRepo.queryList(new SysRolePmsn(null, userId));
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
        return null != this.rolePmsnRepo.queryOne(new SysRolePmsn(roleId, userId));
    }

    /**
     * 添加一个关联关系
     * @param pmsnId
     * @param roleId
     */
    public void addBy(String pmsnId, String roleId) {
        if (StringUtils.isAnyBlank(pmsnId, roleId)) return;
        if (this.existBy(pmsnId, roleId)) return;
        this.rolePmsnRepo.addBy(new SysRolePmsn(roleId, pmsnId));
    }

    /**
     * 删除一个关联关系
     * @param pmsnId
     * @param roleId
     */
    public void removeBy(String pmsnId, String roleId) {
        if (StringUtils.isAnyBlank(pmsnId, roleId)) return;
        this.rolePmsnRepo.removeBy(new SysRolePmsn(roleId, pmsnId));
    }

    /**
     * 通过权限方向，删除关联关系
     * @param pmsnId
     * @param roleIds   如果为空，则删除所有关联的角色
     */
    public void removeByPmsnId(String pmsnId, Set<String> roleIds) {
        if (StringUtils.isBlank(pmsnId)) return;
        this.rolePmsnRepo.removeByPmsnId(pmsnId, roleIds);
    }

    /**
     * 通过角色方向，删除关联关系
     * @param roleId
     * @param userIds   如果为空，则删除所有关联的权限
     */
    public void removeByRoleId(String roleId, Set<String> userIds) {
        if (StringUtils.isBlank(roleId)) return;
        this.rolePmsnRepo.removeByRoleId(roleId, userIds);
    }
}
