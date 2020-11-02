package com.yffd.jecap.admin.sys.infrastructure.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yffd.jecap.admin.base.dao.mybatis.MybatisplusBaseDao;
import com.yffd.jecap.admin.sys.domain.role.entity.SysRole;
import com.yffd.jecap.admin.sys.infrastructure.query.param.RoleQryParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 系统-角色表 Mapper 接口
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Mapper
public interface ISysRoleDao extends MybatisplusBaseDao<SysRole> {

    @Override
    default Wrapper<SysRole> getWrapper(SysRole entity) {
        LambdaQueryWrapper<SysRole> wrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(entity.getRoleId())) wrapper.eq(SysRole::getRoleId, entity.getRoleId());
        if (StringUtils.isNotBlank(entity.getRoleName())) wrapper.like(SysRole::getRoleName, entity.getRoleName());
        if (StringUtils.isNotBlank(entity.getRoleCode())) wrapper.eq(SysRole::getRoleCode, entity.getRoleCode());
        if (StringUtils.isNotBlank(entity.getRoleStatus())) wrapper.eq(SysRole::getRoleStatus, entity.getRoleStatus());
        return wrapper;
    }

    @Select("<script> " +
            "SELECT t1.* FROM sys_role t1 " +
            "WHERE t1.role_id in (SELECT DISTINCT(role_id) FROM sys_user_role WHERE user_id = #{param.userId}) " +
            "<if test='param.roleStatus != null and param.roleStatus != \"\"'> and t1.role_status = #{param.roleStatus} </if> " +
            "<if test='param.roleName != null and param.roleName != \"\"'> and t1.role_name like concat('%',#{param.roleName},'%') </if> " +
            "<if test='param.roleCode != null and param.roleCode != \"\"'> and t1.role_code = #{param.roleCode} </if> " +
            "ORDER BY t1.role_code ASC " +
            "</script>")
    Page<SysRole> queryRltUser(Page page, @Param("param") RoleQryParam param);

    @Select("<script> " +
            "SELECT t1.* FROM sys_role t1 " +
            "WHERE t1.role_id not in (SELECT DISTINCT(role_id) FROM sys_user_role WHERE user_id = #{param.userId}) " +
            "<if test='param.roleStatus != null and param.roleStatus != \"\"'> and t1.role_status = #{param.roleStatus} </if> " +
            "<if test='param.roleName != null and param.roleName != \"\"'> and t1.role_name like concat('%',#{param.roleName},'%') </if> " +
            "<if test='param.roleCode != null and param.roleCode != \"\"'> and t1.role_code = #{param.roleCode} </if> " +
            "ORDER BY t1.role_code ASC " +
            "</script>")
    Page<SysRole> queryNotRltUser(Page page, @Param("param") RoleQryParam param);


}
