package com.yffd.jecap.admin.sys.infrastructure.dao.api;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yffd.jecap.admin.sys.domain.api.entity.SysApiPmsn;
import com.yffd.jecap.admin.base.dao.mybatis.MybatisplusBaseDao;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统-API&权限关联表 Mapper 接口
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Mapper
public interface ISysApiPmsnDao extends MybatisplusBaseDao<SysApiPmsn> {

    @Override
    default Wrapper<SysApiPmsn> getWrapper(SysApiPmsn entity) {
        QueryWrapper<SysApiPmsn> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(entity.getId())) wrapper.eq("id", entity.getId());
        if (StringUtils.isNotBlank(entity.getApiId())) wrapper.eq("api_id", entity.getApiId());
        if (StringUtils.isNotBlank(entity.getPmsnId())) wrapper.eq("pmsn_id", entity.getPmsnId());
        return wrapper;
    }
}
