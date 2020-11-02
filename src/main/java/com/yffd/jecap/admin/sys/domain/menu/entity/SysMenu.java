package com.yffd.jecap.admin.sys.domain.menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yffd.jecap.admin.base.entity.IBaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统-菜单表
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class SysMenu implements IBaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private String menuId;

    /**
     * 父ID
     */
    private String parentId;

    /**
     * 父路径
     */
    private String parentPath;

    /**
     * 权限ID
     */
    private String pmsnId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单显示状态，1=显示、0=隐藏
     */
    private String menuShowStatus;

    /**
     * 菜单排序号
     */
    private String menuShowNum;

    /**
     * 菜单图标
     */
    private String menuShowIcon;

    /**
     * 菜单添加时间
     */
    private LocalDateTime menuAddTime;

    public SysMenu(String menuName) {
        this.menuName = menuName;
        this.initValue();
    }

    public void initValue() {
        if (StringUtils.isBlank(this.menuShowStatus)) this.menuShowStatus = "1";
        if (StringUtils.isBlank(this.menuShowNum)) this.menuShowNum = "0001";
        if (null == this.menuAddTime) this.menuAddTime = LocalDateTime.now();
    }
}
