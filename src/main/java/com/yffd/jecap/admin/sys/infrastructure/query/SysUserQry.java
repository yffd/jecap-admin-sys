package com.yffd.jecap.admin.sys.infrastructure.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yffd.jecap.admin.base.helper.PageHelper;
import com.yffd.jecap.admin.base.page.PageData;
import com.yffd.jecap.admin.sys.domain.role.entity.SysRole;
import com.yffd.jecap.admin.sys.domain.user.entity.SysUser;
import com.yffd.jecap.admin.sys.infrastructure.dao.ISysUserDao;
import com.yffd.jecap.admin.sys.infrastructure.query.param.UserQryParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysUserQry {
    @Autowired private ISysUserDao userDao;

    public Object queryBy(UserQryParam model) {
        if (null == model) return null;
        QueryWrapper wrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(model.getUserCode())) wrapper.eq("user_code", model.getUserCode());
        if (StringUtils.isNotBlank(model.getUserPhone())) wrapper.eq("user_phone", model.getUserPhone());
        if (StringUtils.isNotBlank(model.getUserEmail())) wrapper.eq("user_email", model.getUserEmail());
        if (StringUtils.isNotBlank(model.getUserStatus())) wrapper.eq("user_status", model.getUserStatus());
        wrapper.orderByDesc("create_time");
        Page<SysRole> page = this.userDao.selectPage(PageHelper.toPage(model), wrapper);
        return PageHelper.fromPage(page);
    }

    /**
     * 查询某个角色已拥有的用户
     * @param model
     * @return
     */
    public PageData<SysUser> queryByRltRole(UserQryParam model) {
        if (null == model || StringUtils.isBlank(model.getRoleId())) return null;
        if (StringUtils.isBlank(model.getUserStatus())) model.setUserStatus("1");
        Page<SysUser> page = this.userDao.queryRltRole(PageHelper.toPage(model), model);
        return PageHelper.fromPage(page);
    }

    /**
     * 查询某个角色未拥有的用户
     * @param model
     * @return
     */
    public PageData<SysUser> queryByNotRltRole(UserQryParam model) {
        if (null == model || StringUtils.isBlank(model.getRoleId())) return null;
        if (StringUtils.isBlank(model.getUserStatus())) model.setUserStatus("1");
        Page<SysUser> page = this.userDao.queryNotRltRole(PageHelper.toPage(model), model);
        return PageHelper.fromPage(page);
    }

}
