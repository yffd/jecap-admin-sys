package com.yffd.jecap.admin.sys.infrastructure.dao.pmsn;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yffd.jecap.admin.sys.domain.pmsn.entity.SysPmsn;
import com.yffd.jecap.admin.base.dao.mybatis.MybatisplusBaseDao;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统-权限表 Mapper 接口
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Mapper
public interface ISysPmsnDao extends MybatisplusBaseDao<SysPmsn> {

    @Override
    default Wrapper<SysPmsn> getWrapper(SysPmsn entity) {
        QueryWrapper<SysPmsn> wrapper = new QueryWrapper<>();
        if (null != entity.getId()) wrapper.eq("id", entity.getId());
        if (StringUtils.isNotBlank(entity.getPmsnName())) wrapper.like("pmsn_name", entity.getPmsnName());
        if (StringUtils.isNotBlank(entity.getPmsnCode())) wrapper.eq("pmsn_code", entity.getPmsnCode());
        if (StringUtils.isNotBlank(entity.getPmsnType())) wrapper.eq("pmsn_type", entity.getPmsnType());
        if (StringUtils.isNotBlank(entity.getPmsnStatus())) wrapper.eq("pmsn_status", entity.getPmsnStatus());
        return wrapper;
    }
}
