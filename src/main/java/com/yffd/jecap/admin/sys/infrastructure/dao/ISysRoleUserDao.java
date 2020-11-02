package com.yffd.jecap.admin.sys.infrastructure.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yffd.jecap.admin.base.dao.mybatis.MybatisplusBaseDao;
import com.yffd.jecap.admin.sys.domain.rlt.entity.SysRoleUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统-角色&用户关联表 Mapper 接口
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Mapper
public interface ISysRoleUserDao extends MybatisplusBaseDao<SysRoleUser> {

    @Override
    default Wrapper<SysRoleUser> getWrapper(SysRoleUser entity) {
        LambdaQueryWrapper<SysRoleUser> wrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(entity.getUserId())) wrapper.eq(SysRoleUser::getUserId, entity.getUserId());
        if (StringUtils.isNotBlank(entity.getRoleId())) wrapper.eq(SysRoleUser::getRoleId, entity.getRoleId());
        return wrapper;
    }

}
