package com.yffd.jecap.admin.sys.infrastructure.dao.job;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yffd.jecap.admin.base.dao.mybatis.MybatisplusBaseDao;
import com.yffd.jecap.admin.sys.domain.job.entity.SysJob;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统-岗位表 Mapper 接口
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Mapper
public interface ISysJobDao extends MybatisplusBaseDao<SysJob> {

    @Override
    default Wrapper<SysJob> getWrapper(SysJob entity) {
        QueryWrapper<SysJob> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(entity.getId())) wrapper.eq("id", entity.getId());
        if (StringUtils.isNotBlank(entity.getJobName())) wrapper.like("job_name", entity.getJobName());
        if (StringUtils.isNotBlank(entity.getJobCode())) wrapper.eq("job_code", entity.getJobCode());
        return wrapper;
    }
}
