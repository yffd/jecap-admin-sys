package com.yffd.jecap.admin.sys.domain.rlt.entity;

import com.yffd.jecap.admin.base.entity.IBaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统-用户&岗位关联表
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class SysUserJob implements IBaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 岗位ID
     */
    private String jobId;

    public SysUserJob(String userId, String jobId) {
        this.userId = userId;
        this.jobId = jobId;
    }
}
