package com.yffd.jecap.admin.sys.infrastructure.dao.group;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yffd.jecap.admin.sys.domain.group.entity.SysGroup;
import com.yffd.jecap.admin.base.dao.mybatis.MybatisplusBaseDao;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统-组表 Mapper 接口
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Mapper
public interface ISysGroupDao extends MybatisplusBaseDao<SysGroup> {

    @Override
    default Wrapper<SysGroup> getWrapper(SysGroup entity) {
        QueryWrapper<SysGroup> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(entity.getId())) wrapper.eq("id", entity.getId());
        if (StringUtils.isNotBlank(entity.getGroupName())) wrapper.like("group_name", entity.getGroupName());
        if (StringUtils.isNotBlank(entity.getGroupCode())) wrapper.eq("group_code", entity.getGroupCode());
        if (StringUtils.isNotBlank(entity.getGroupStatus())) wrapper.eq("group_status", entity.getGroupStatus());
        if (StringUtils.isNotBlank(entity.getGroupType())) wrapper.eq("group_type", entity.getGroupType());
        return wrapper;
    }
}
