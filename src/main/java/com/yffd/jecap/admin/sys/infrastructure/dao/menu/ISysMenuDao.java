package com.yffd.jecap.admin.sys.infrastructure.dao.menu;

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
        if (StringUtils.isNotBlank(entity.getId())) wrapper.eq("id", entity.getId());
        if (StringUtils.isNotBlank(entity.getPid())) wrapper.eq("pid", entity.getPid());
        if (StringUtils.isNotBlank(entity.getPath())) wrapper.likeRight("path", entity.getPath());
        if (StringUtils.isNotBlank(entity.getMenuName())) wrapper.like("menu_name", entity.getMenuName());
        if (StringUtils.isNotBlank(entity.getMenuShow())) wrapper.eq("menu_show", entity.getMenuShow());
        return wrapper;
    }

}
