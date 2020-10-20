package com.yffd.jecap.admin.sys.domain.group.entity;

import com.yffd.jecap.admin.base.entity.IBaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统-组表
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Data
@Accessors(chain = true)
public class SysGroup implements IBaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 组名称
     */
    private String groupName;

    /**
     * 组编号，唯一
     */
    private String groupCode;

    /**
     * 组类型，1=用户组、2=角色组
     */
    private String groupType;

    /**
     * 组状态，1=启用、0=禁用
     */
    private String groupStatus;

    public SysGroup(String groupName, String groupCode, String groupType) {
        this.groupName = groupName;
        this.groupCode = groupCode;
        this.groupType = groupType;
        this.groupStatus = "1";
    }
}
