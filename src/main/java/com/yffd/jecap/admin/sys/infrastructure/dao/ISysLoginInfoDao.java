package com.yffd.jecap.admin.sys.infrastructure.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yffd.jecap.admin.base.dao.mybatis.MybatisplusBaseDao;
import com.yffd.jecap.admin.sys.domain.login.entity.SysLoginInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统-登录信息表 Mapper 接口
 * </p>
 *
 * @author ZhangST
 * @since 2020-10-29
 */
@Mapper
public interface ISysLoginInfoDao extends MybatisplusBaseDao<SysLoginInfo> {

    @Override
    default Wrapper<SysLoginInfo> getWrapper(SysLoginInfo entity) {
        LambdaQueryWrapper<SysLoginInfo> wrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(entity.getId())) wrapper.eq(SysLoginInfo::getId, entity.getId());
        if (StringUtils.isNotBlank(entity.getTokenValue())) wrapper.eq(SysLoginInfo::getTokenValue, entity.getTokenValue());
        if (StringUtils.isNotBlank(entity.getAcntId())) wrapper.eq(SysLoginInfo::getAcntId, entity.getAcntId());
        if (StringUtils.isNotBlank(entity.getAcntName())) wrapper.eq(SysLoginInfo::getAcntName, entity.getAcntName());
        if (StringUtils.isNotBlank(entity.getUserId())) wrapper.eq(SysLoginInfo::getUserId, entity.getUserId());
        return wrapper;
    }
}
