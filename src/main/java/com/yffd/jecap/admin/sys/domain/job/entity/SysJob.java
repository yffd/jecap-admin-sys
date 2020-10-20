package com.yffd.jecap.admin.sys.domain.job.entity;

import com.yffd.jecap.admin.base.entity.IBaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统-岗位表
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Data
@Accessors(chain = true)
public class SysJob implements IBaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 岗位名称
     */
    private String jobName;

    /**
     * 岗位编号
     */
    private String jobCode;


}
