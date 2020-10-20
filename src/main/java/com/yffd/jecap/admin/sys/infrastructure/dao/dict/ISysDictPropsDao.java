package com.yffd.jecap.admin.sys.infrastructure.dao.dict;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yffd.jecap.admin.base.dao.mybatis.MybatisplusBaseDao;
import com.yffd.jecap.admin.sys.domain.dict.entity.SysDictProps;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统-字典属性表 Mapper 接口
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Mapper
public interface ISysDictPropsDao extends MybatisplusBaseDao<SysDictProps> {

    @Override
    default Wrapper<SysDictProps> getWrapper(SysDictProps entity) {
        QueryWrapper<SysDictProps> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(entity.getId())) wrapper.eq("id", entity.getId());
        if (StringUtils.isNotBlank(entity.getDictId())) wrapper.eq("dict_id", entity.getDictId());
        if (StringUtils.isNotBlank(entity.getPropsName())) wrapper.likeRight("props_name", entity.getPropsName());
        if (StringUtils.isNotBlank(entity.getPropsCode())) wrapper.eq("props_code", entity.getPropsCode());
        if (StringUtils.isNotBlank(entity.getPropsType())) wrapper.eq("props_type", entity.getPropsType());
        return wrapper;
    }
}
