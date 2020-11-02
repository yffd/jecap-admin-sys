package com.yffd.jecap.admin.sys.infrastructure.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yffd.jecap.admin.base.dao.mybatis.MybatisplusBaseDao;
import com.yffd.jecap.admin.sys.domain.org.entity.SysOrg;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统-组织表 Mapper 接口
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Mapper
public interface ISysOrgDao extends MybatisplusBaseDao<SysOrg> {

    @Override
    default Wrapper<SysOrg> getWrapper(SysOrg entity) {
        QueryWrapper<SysOrg> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(entity.getId())) wrapper.eq("id", entity.getId());
        if (StringUtils.isNotBlank(entity.getPid())) wrapper.eq("pid", entity.getPid());
        if (StringUtils.isNotBlank(entity.getPath())) wrapper.likeRight("path", entity.getPath());
        if (StringUtils.isNotBlank(entity.getOrgName())) wrapper.like("org_name", entity.getOrgName());
        if (StringUtils.isNotBlank(entity.getOrgCode())) wrapper.eq("org_code", entity.getOrgCode());
        if (StringUtils.isNotBlank(entity.getOrgType())) wrapper.eq("org_type", entity.getOrgType());
        return wrapper;
    }

}
