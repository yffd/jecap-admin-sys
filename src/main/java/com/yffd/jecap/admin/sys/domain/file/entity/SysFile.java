package com.yffd.jecap.admin.sys.domain.file.entity;

import com.yffd.jecap.admin.base.entity.IBaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统-文件表
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Data
@Accessors(chain = true)
public class SysFile implements IBaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 本地文件名称
     */
    private String localFileName;

    /**
     * 本地文件路径
     */
    private String localFilePath;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
