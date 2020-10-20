package com.yffd.jecap.admin.sys.domain.pmsn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yffd.jecap.admin.base.entity.IBaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统-权限表
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class SysPmsn implements IBaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private String id;

    /**
     * 权限名称
     */
    private String pmsnName;

    /**
     * 权限编号
     */
    private String pmsnCode;

    /**
     * 权限类型，1=页面、2=操作、3=数据
     */
    private String pmsnType;

    /**
     * 权限状态，1=启用、0=禁用
     */
    private String pmsnStatus;

    public SysPmsn(String pmsnName, String pmsnCode) {
        this.pmsnName = pmsnName;
        this.pmsnCode = pmsnCode;
    }

    public SysPmsn(String pmsnName, String pmsnType, String pmsnStatus) {
        this.pmsnName = pmsnName;
        this.pmsnType = pmsnType;
        this.pmsnStatus = pmsnStatus;
    }

    public SysPmsn(String pmsnName, String pmsnCode, String pmsnType, String pmsnStatus) {
        this.pmsnName = pmsnName;
        this.pmsnCode = pmsnCode;
        this.pmsnType = pmsnType;
        this.pmsnStatus = pmsnStatus;
    }
}
