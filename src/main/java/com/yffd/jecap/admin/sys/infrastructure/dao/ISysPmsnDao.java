package com.yffd.jecap.admin.sys.infrastructure.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yffd.jecap.admin.base.dao.mybatis.MybatisplusBaseDao;
import com.yffd.jecap.admin.sys.domain.pmsn.entity.SysPmsn;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Set;

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
        LambdaQueryWrapper<SysPmsn> wrapper = Wrappers.lambdaQuery();
        if (null != entity.getPmsnId()) wrapper.eq(SysPmsn::getPmsnId, entity.getPmsnId());
        if (StringUtils.isNotBlank(entity.getPmsnName())) wrapper.eq(SysPmsn::getPmsnName, entity.getPmsnName());
        if (StringUtils.isNotBlank(entity.getPmsnCode())) wrapper.eq(SysPmsn::getPmsnCode, entity.getPmsnCode());
        if (StringUtils.isNotBlank(entity.getPmsnStatus())) wrapper.eq(SysPmsn::getPmsnStatus, entity.getPmsnStatus());
        if (StringUtils.isNotBlank(entity.getPmsnType())) wrapper.eq(SysPmsn::getPmsnType, entity.getPmsnType());
        return wrapper;
    }

    @Update("<script>" +
            "UPDATE sys_pmsn SET pmsn_status = #{pmsnStatus} WHERE pmsn_id in <foreach collection='pmsnIds' index='index' item='item' open='(' separator=',' close=')'> #{item} </foreach> " +
            "</script>")
    void modifyBatchPmsnStatus(@Param("pmsnIds") Set<String> pmsnIds, @Param("pmsnStatus") String pmsnStatus);
}
