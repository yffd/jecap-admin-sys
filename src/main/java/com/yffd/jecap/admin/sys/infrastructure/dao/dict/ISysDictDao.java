package com.yffd.jecap.admin.sys.infrastructure.dao.dict;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yffd.jecap.admin.base.dao.mybatis.MybatisplusBaseDao;
import com.yffd.jecap.admin.sys.domain.dict.entity.SysDict;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统-字典表 Mapper 接口
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Mapper
public interface ISysDictDao extends MybatisplusBaseDao<SysDict> {

    @Override
    default Wrapper<SysDict> getWrapper(SysDict entity) {
        QueryWrapper<SysDict> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(entity.getId())) wrapper.eq("id", entity.getId());
        if (StringUtils.isNotBlank(entity.getPid())) wrapper.eq("pid", entity.getPid());
        if (StringUtils.isNotBlank(entity.getPath())) wrapper.likeRight("path", entity.getPath());
        if (StringUtils.isNotBlank(entity.getItemName())) wrapper.like("item_name", entity.getItemName());
        if (StringUtils.isNotBlank(entity.getItemCode())) wrapper.eq("item_code", entity.getItemCode());
        if (StringUtils.isNotBlank(entity.getPropsFlag())) wrapper.eq("props_flag", entity.getPropsFlag());
        return wrapper;
    }
}
