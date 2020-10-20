package com.yffd.jecap.admin.sys.domain.area.entity;

import com.yffd.jecap.admin.base.entity.IBaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统-区域表
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class SysArea implements IBaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 父ID
     */
    private String pid;

    /**
     * 路径
     */
    private String path;

    /**
     * 级别，1=国家、2=省、3=市、4=县
     */
    private String areaLevel;

    /**
     * 名称
     */
    private String areaName;

    /**
     * 简拼
     */
    private String areaJianpin;

    public SysArea(String areaLevel, String areaName) {
        this.areaLevel = areaLevel;
        this.areaName = areaName;
    }

}
