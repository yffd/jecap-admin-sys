package com.yffd.jecap.admin.sys.infrastructure.dao.menu;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yffd.jecap.admin.base.dao.mybatis.MybatisplusBaseDao;
import com.yffd.jecap.admin.sys.domain.menu.entity.SysMenuPmsn;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统-菜单&权限关联表 Mapper 接口
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Mapper
public interface ISysMenuPmsnDao extends MybatisplusBaseDao<SysMenuPmsn> {

    @Override
    default Wrapper<SysMenuPmsn> getWrapper(SysMenuPmsn entity) {
        QueryWrapper<SysMenuPmsn> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(entity.getId())) wrapper.eq("id", entity.getId());
        if (StringUtils.isNotBlank(entity.getMenuId())) wrapper.likeLeft("menu_id", entity.getMenuId());
        if (StringUtils.isNotBlank(entity.getPmsnId())) wrapper.likeRight("pmsn_id", entity.getPmsnId());
        return wrapper;
    }

}
