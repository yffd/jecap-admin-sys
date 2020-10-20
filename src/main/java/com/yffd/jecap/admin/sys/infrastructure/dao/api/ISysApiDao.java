package com.yffd.jecap.admin.sys.infrastructure.dao.api;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yffd.jecap.admin.sys.domain.api.entity.SysApi;
import com.yffd.jecap.admin.base.dao.mybatis.MybatisplusBaseDao;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统-API接口表 Mapper 接口
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Mapper
public interface ISysApiDao extends MybatisplusBaseDao<SysApi> {

    @Override
    default Wrapper<SysApi> getWrapper(SysApi entity) {
        QueryWrapper<SysApi> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(entity.getId())) wrapper.eq("id", entity.getId());
        if (StringUtils.isNotBlank(entity.getApiName())) wrapper.like("api_name", entity.getApiName());
        if (StringUtils.isNotBlank(entity.getApiUrl())) wrapper.eq("api_url", entity.getApiUrl());
        return wrapper;
    }
}
