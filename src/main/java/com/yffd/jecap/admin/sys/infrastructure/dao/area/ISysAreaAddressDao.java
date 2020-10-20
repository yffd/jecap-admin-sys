package com.yffd.jecap.admin.sys.infrastructure.dao.area;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yffd.jecap.admin.base.dao.mybatis.MybatisplusBaseDao;
import com.yffd.jecap.admin.sys.domain.area.entity.SysAreaAddress;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统-区域地址表 Mapper 接口
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Mapper
public interface ISysAreaAddressDao extends MybatisplusBaseDao<SysAreaAddress> {

    @Override
    default Wrapper<SysAreaAddress> getWrapper(SysAreaAddress entity) {
        QueryWrapper<SysAreaAddress> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(entity.getId())) wrapper.eq("id", entity.getId());
        if (StringUtils.isNotBlank(entity.getAreaId())) wrapper.eq("area_id", entity.getAreaId());
        return wrapper;
    }
}
