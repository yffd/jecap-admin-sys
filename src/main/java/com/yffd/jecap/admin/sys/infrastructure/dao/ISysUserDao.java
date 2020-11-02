package com.yffd.jecap.admin.sys.infrastructure.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yffd.jecap.admin.base.dao.mybatis.MybatisplusBaseDao;
import com.yffd.jecap.admin.sys.domain.user.entity.SysUser;
import com.yffd.jecap.admin.sys.infrastructure.query.param.UserQryParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * <p>
 * 系统-用户表 Mapper 接口
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Mapper
public interface ISysUserDao extends MybatisplusBaseDao<SysUser> {

    @Override
    default Wrapper<SysUser> getWrapper(SysUser entity) {
        LambdaQueryWrapper<SysUser> wrapper = Wrappers.lambdaQuery();
        if (null != entity) {
            if (StringUtils.isNotBlank(entity.getUserId())) wrapper.eq(SysUser::getUserId, entity.getUserId());
            if (StringUtils.isNotBlank(entity.getUserCode())) wrapper.eq(SysUser::getUserCode, entity.getUserCode());
            if (StringUtils.isNotBlank(entity.getUserPhone())) wrapper.eq(SysUser::getUserPhone, entity.getUserPhone());
            if (StringUtils.isNotBlank(entity.getUserEmail())) wrapper.eq(SysUser::getUserEmail, entity.getUserEmail());
            if (StringUtils.isNotBlank(entity.getUserStatus())) wrapper.eq(SysUser::getUserStatus, entity.getUserStatus());
            if (StringUtils.isNotBlank(entity.getLoginAcntId())) wrapper.eq(SysUser::getLoginAcntId, entity.getLoginAcntId());
        }
        return wrapper;
    }

    @Select("<script> " +
            "SELECT t1.* FROM sys_user t1 " +
            "WHERE t1.user_id in (SELECT DISTINCT(user_id) FROM sys_user_role WHERE role_id = #{param.roleId}) " +
            "<if test='param.userStatus != null and param.userStatus != \"\"'> and t1.user_status = #{param.userStatus} </if> " +
            "<if test='param.userName != null and param.userName != \"\"'> and t1.user_name like concat('%',#{param.userName},'%')  </if> " +
            "<if test='param.userCode != null and param.userCode != \"\"'> and t1.user_code = #{param.userCode} </if> " +
            "<if test='param.userPhone != null and param.userPhone != \"\"'> and t1.user_phone = #{param.userPhone} </if> " +
            "<if test='param.userEmail != null and param.userEmail != \"\"'> and t1.user_email = #{param.userEmail} </if> " +
            "ORDER BY t1.user_code ASC " +
            "</script>")
    Page<SysUser> queryRltRole(Page page, @Param("param") UserQryParam param);

    @Select("<script> " +
            "SELECT t1.* FROM sys_user t1 " +
            "WHERE t1.user_id not in (SELECT DISTINCT(user_id) FROM sys_user_role WHERE role_id = #{param.roleId}) " +
            "<if test='param.userStatus != null and param.userStatus != \"\"'> and t1.user_status = #{param.userStatus} </if> " +
            "<if test='param.userName != null and param.userName != \"\"'> and t1.user_name like concat('%',#{param.userName},'%')  </if> " +
            "<if test='param.userCode != null and param.userCode != \"\"'> and t1.user_code = #{param.userCode} </if> " +
            "<if test='param.userPhone != null and param.userPhone != \"\"'> and t1.user_phone = #{param.userPhone} </if> " +
            "<if test='param.userEmail != null and param.userEmail != \"\"'> and t1.user_email = #{param.userEmail} </if> " +
            "ORDER BY t1.user_code ASC " +
            "</script> ")
    Page<SysUser> queryNotRltRole(Page page, @Param("param") UserQryParam param);

    @Select("<script> " +
            "SELECT role_code FROM sys_role WHERE role_status = '1' and role_id in (SELECT role_id FROM sys_role_user WHERE user_id = #{userId})" +
            "</script> ")
    Set<String> queryRltRoleCode(String userId);

    @Select("<script> " +
            "SELECT t3.pmsn_code FROM sys_pmsn t3 WHERE t3.pmsn_status = '1' and t3.pmsn_id in \n" +
            "(\n" +
            "SELECT t2.pmsn_id FROM sys_role t1 \n" +
            "INNER JOIN sys_role_pmsn t2 ON t1.role_id = t2.role_id\n" +
            "WHERE t1.role_status = '1' and t1.role_id in (SELECT role_id FROM sys_role_user WHERE user_id = #{userId})\n" +
            ")" +
            "</script> ")
    Set<String> queryRltPmsnCode(String userId);
}
