package com.yffd.jecap.admin.sys.infrastructure.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yffd.jecap.admin.base.helper.PageHelper;
import com.yffd.jecap.admin.base.page.PageData;
import com.yffd.jecap.admin.sys.domain.role.entity.SysRole;
import com.yffd.jecap.admin.sys.infrastructure.dao.ISysRoleDao;
import com.yffd.jecap.admin.sys.infrastructure.query.param.RoleQryParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysRoleQry {
    @Autowired private ISysRoleDao roleDao;

    public PageData<SysRole> queryBy(RoleQryParam model) {
        if (null == model) return null;
        QueryWrapper wrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(model.getRoleName())) wrapper.like("role_name", model.getRoleName());
        if (StringUtils.isNotBlank(model.getRoleCode())) wrapper.eq("role_code", model.getRoleCode());
        if (StringUtils.isNotBlank(model.getRoleStatus())) wrapper.eq("role_status", model.getRoleStatus());
        wrapper.orderByDesc("role_add_time");
        Page<SysRole> page = this.roleDao.selectPage(PageHelper.toPage(model), wrapper);
        return PageHelper.fromPage(page);
    }

    /**
     * 查询某个用户已关联的角色
     * @param model
     * @return
     */
    public PageData<SysRole> queryByRltUser(RoleQryParam model) {
        if (null == model || StringUtils.isBlank(model.getUserId())) return null;
        if (StringUtils.isBlank(model.getRoleStatus())) model.setRoleStatus("1");
        Page<SysRole> page = this.roleDao.queryRltUser(PageHelper.toPage(model), model);
        return PageHelper.fromPage(page);
    }

    /**
     * 查询某个用户未关联的角色
     * @param model
     * @return
     */
    public PageData<SysRole> queryByNotRltUser(RoleQryParam model) {
        if (null == model || StringUtils.isBlank(model.getUserId())) return null;
        if (StringUtils.isBlank(model.getRoleStatus())) model.setRoleStatus("1");
        Page<SysRole> page = this.roleDao.queryNotRltUser(PageHelper.toPage(model), model);
        return PageHelper.fromPage(page);
    }

}
