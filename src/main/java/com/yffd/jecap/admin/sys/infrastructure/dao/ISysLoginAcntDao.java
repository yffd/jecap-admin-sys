package com.yffd.jecap.admin.sys.infrastructure.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yffd.jecap.admin.base.dao.mybatis.MybatisplusBaseDao;
import com.yffd.jecap.admin.sys.domain.login.entity.SysLoginAcnt;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统-登录账户表 Mapper 接口
 * </p>
 *
 * @author ZhangST
 * @since 2020-10-28
 */
@Mapper
public interface ISysLoginAcntDao extends MybatisplusBaseDao<SysLoginAcnt> {

    @Override
    default Wrapper<SysLoginAcnt> getWrapper(SysLoginAcnt entity) {
        LambdaQueryWrapper<SysLoginAcnt> wrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(entity.getAcntId())) wrapper.eq(SysLoginAcnt::getAcntId, entity.getAcntId());
        if (StringUtils.isNotBlank(entity.getAcntName())) wrapper.eq(SysLoginAcnt::getAcntName, entity.getAcntName());
        return wrapper;
    }
}
