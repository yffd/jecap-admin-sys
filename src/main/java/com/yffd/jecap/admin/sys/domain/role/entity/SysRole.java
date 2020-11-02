package com.yffd.jecap.admin.sys.domain.role.entity;

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
 * 系统-角色表
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class SysRole implements IBaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编号
     */
    private String roleCode;

    /**
     * 角色状态，1=启用、0=禁用
     */
    private String roleStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    public SysRole(String roleName, String roleCode) {
        this.roleName = roleName;
        this.roleCode = roleCode;
    }

    public SysRole initValue() {
        if (StringUtils.isBlank(this.roleStatus)) this.roleStatus = "1";
        if (null == this.createTime) this.createTime = LocalDateTime.now();
        return this;
    }
}
