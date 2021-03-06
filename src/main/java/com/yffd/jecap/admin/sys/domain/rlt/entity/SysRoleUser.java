package com.yffd.jecap.admin.sys.domain.rlt.entity;

import com.yffd.jecap.admin.base.entity.IBaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统-用户&角色关联表
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class SysRoleUser implements IBaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 用户ID
     */
    private String userId;

    public SysRoleUser(String roleId, String userId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
