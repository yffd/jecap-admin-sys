package com.yffd.jecap.admin.sys.infrastructure.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yffd.jecap.admin.base.dao.mybatis.MybatisplusBaseDao;
import com.yffd.jecap.admin.sys.domain.menu.entity.SysMenu;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统-菜单表 Mapper 接口
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Mapper
public interface ISysMenuDao extends MybatisplusBaseDao<SysMenu> {

    @Override
    default Wrapper<SysMenu> getWrapper(SysMenu entity) {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(entity.getMenuId())) wrapper.eq("menu_id", entity.getMenuId());
        if (StringUtils.isNotBlank(entity.getParentId())) wrapper.eq("parent_id", entity.getParentId());
        if (StringUtils.isNotBlank(entity.getParentPath())) wrapper.likeRight("parent_path", entity.getParentPath());
        if (StringUtils.isNotBlank(entity.getMenuName())) wrapper.eq("menu_name", entity.getMenuName());
        if (StringUtils.isNotBlank(entity.getMenuShowStatus())) wrapper.eq("menu_show", entity.getMenuShowStatus());
        if (StringUtils.isNotBlank(entity.getPmsnId())) wrapper.eq("pmsn_id", entity.getPmsnId());
        return wrapper;
    }

}
