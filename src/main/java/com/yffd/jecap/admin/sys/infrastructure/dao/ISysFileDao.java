package com.yffd.jecap.admin.sys.infrastructure.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yffd.jecap.admin.base.dao.mybatis.MybatisplusBaseDao;
import com.yffd.jecap.admin.sys.domain.file.entity.SysFile;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统-文件表 Mapper 接口
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Mapper
public interface ISysFileDao extends MybatisplusBaseDao<SysFile> {

    @Override
    default Wrapper<SysFile> getWrapper(SysFile entity) {
        QueryWrapper<SysFile> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(entity.getId())) wrapper.eq("id", entity.getId());
        if (StringUtils.isNotBlank(entity.getFileName())) wrapper.like("file_name", entity.getFileName());
        if (StringUtils.isNotBlank(entity.getCreateBy())) wrapper.eq("create_by", entity.getCreateBy());
        if (null != entity.getCreateTime()) wrapper.eq("create_time", entity.getCreateTime());
        return wrapper;
    }
}
