package com.yffd.jecap.admin.sys.domain.menu.entity;

import com.yffd.jecap.admin.base.entity.IBaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统-菜单&权限关联表
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class SysMenuPmsn implements IBaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 菜单ID
     */
    private String menuId;

    /**
     * 权限ID
     */
    private String pmsnId;

    public SysMenuPmsn(String menuId, String pmsnId) {
        this.menuId = menuId;
        this.pmsnId = pmsnId;
    }
}
