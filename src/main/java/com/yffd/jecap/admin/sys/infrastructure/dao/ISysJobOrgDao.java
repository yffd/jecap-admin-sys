package com.yffd.jecap.admin.sys.infrastructure.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yffd.jecap.admin.sys.domain.job.entity.SysJobOrg;
import com.yffd.jecap.admin.base.dao.mybatis.MybatisplusBaseDao;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统-岗位&组织关联表 Mapper 接口
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Mapper
public interface ISysJobOrgDao extends MybatisplusBaseDao<SysJobOrg> {

    @Override
    default Wrapper<SysJobOrg> getWrapper(SysJobOrg entity) {
        QueryWrapper<SysJobOrg> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(entity.getId())) wrapper.eq("id", entity.getId());
        if (StringUtils.isNotBlank(entity.getJobId())) wrapper.eq("job_id", entity.getJobId());
        if (StringUtils.isNotBlank(entity.getOrgId())) wrapper.eq("org_id", entity.getOrgId());
        return wrapper;
    }
}
