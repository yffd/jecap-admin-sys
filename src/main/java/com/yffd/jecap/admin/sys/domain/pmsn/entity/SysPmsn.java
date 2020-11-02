package com.yffd.jecap.admin.sys.domain.pmsn.entity;

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

    public static final String PMSN_STATUS_YES = "1";
    public static final String PMSN_STATUS_NO = "0";

    /**
     * 权限ID
     */
    @TableId(type = IdType.AUTO)
    private String pmsnId;

    /**
     * 权限名称
     */
    private String pmsnName;

    /**
     * 权限编号
     */
    private String pmsnCode;

    /**
     * 权限类型，10=页面元素、20=操作接口、30=数据对象
     */
    private String pmsnType;

    /**
     * 权限状态，1=启用、0=禁用
     */
    private String pmsnStatus;

    /**
     * 父ID
     */
    private String parentId;

    /**
     * 父路径
     */
    private String parentPath;

    /**
     * 创建人ID
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人ID
     */
    private String modifyBy;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;


    public SysPmsn(String pmsnName, String pmsnCode) {
        this.pmsnName = pmsnName;
        this.pmsnCode = pmsnCode;
    }

    public SysPmsn(String pmsnName, String pmsnCode, String pmsnType) {
        this.pmsnName = pmsnName;
        this.pmsnCode = pmsnCode;
        this.pmsnType = pmsnType;
    }

    public SysPmsn initValue() {
        if (StringUtils.isBlank(this.pmsnType)) this.pmsnType = "10";
        if (StringUtils.isBlank(this.pmsnStatus)) this.pmsnStatus = "1";
        if (null == this.createTime) this.createTime = LocalDateTime.now();
        return this;
    }

    public SysPmsn enableStatus() {
        this.pmsnStatus = PMSN_STATUS_YES;
        return this;
    }

    public SysPmsn disableStatus() {
        this.pmsnStatus = PMSN_STATUS_NO;
        return this;
    }
}
