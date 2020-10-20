package com.yffd.jecap.admin.sys.infrastructure.dao.area;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yffd.jecap.admin.base.dao.mybatis.MybatisplusBaseDao;
import com.yffd.jecap.admin.sys.domain.area.entity.SysArea;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统-区域表 Mapper 接口
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Mapper
public interface ISysAreaDao extends MybatisplusBaseDao<SysArea> {

    @Override
    default Wrapper<SysArea> getWrapper(SysArea entity) {
        QueryWrapper<SysArea> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(entity.getId())) wrapper.eq("id", entity.getId());
        if (StringUtils.isNotBlank(entity.getPid())) wrapper.eq("pid", entity.getPid());
        if (StringUtils.isNotBlank(entity.getPath())) wrapper.likeRight("path", entity.getPath());
        if (StringUtils.isNotBlank(entity.getAreaLevel())) wrapper.eq("area_level", entity.getAreaLevel());
        if (StringUtils.isNotBlank(entity.getAreaName())) wrapper.eq("area_name", entity.getAreaName());
        if (StringUtils.isNotBlank(entity.getAreaJianpin())) wrapper.eq("area_jianpin", entity.getAreaJianpin());
        wrapper.orderByAsc("area_level", "area_name");
        return wrapper;
    }
}
