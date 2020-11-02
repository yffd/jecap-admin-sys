package com.yffd.jecap.admin.sys.domain.area.entity;

import com.yffd.jecap.admin.base.entity.IBaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统-区域地址表
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class SysAreaAddress implements IBaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 区域ID
     */
    private String areaId;

    /**
     * 区域地址
     */
    private String areaAddress;

    /**
     * 地理坐标，经纬度(x,y)
     */
    private String coordinate;

    /**
     * 详细地址，街道、门牌号
     */
    private String detailAddress;

    /**
     * 备注
     */
    private String remarks;


}
