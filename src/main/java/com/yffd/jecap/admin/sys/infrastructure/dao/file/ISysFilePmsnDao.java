package com.yffd.jecap.admin.sys.infrastructure.dao.file;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yffd.jecap.admin.base.dao.mybatis.MybatisplusBaseDao;
import com.yffd.jecap.admin.sys.domain.file.entity.SysFilePmsn;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统-文件&权限关联表 Mapper 接口
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Mapper
public interface ISysFilePmsnDao extends MybatisplusBaseDao<SysFilePmsn> {

    @Override
    default Wrapper<SysFilePmsn> getWrapper(SysFilePmsn entity) {
        QueryWrapper<SysFilePmsn> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(entity.getId())) wrapper.eq("id", entity.getId());
        if (StringUtils.isNotBlank(entity.getFileId())) wrapper.eq("file_id", entity.getFileId());
        if (StringUtils.isNotBlank(entity.getPmsnId())) wrapper.eq("pmsn_id", entity.getPmsnId());
        return wrapper;
    }
}
