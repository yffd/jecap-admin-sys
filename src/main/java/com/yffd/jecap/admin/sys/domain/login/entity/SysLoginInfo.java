package com.yffd.jecap.admin.sys.domain.login.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yffd.jecap.admin.base.entity.IBaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统-登录信息表
 * </p>
 *
 * @author ZhangST
 * @since 2020-10-29
 */
@Data
@Accessors(chain = true)
public class SysLoginInfo implements IBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private String id;

    /**
     * 登录令牌值
     */
    private String tokenValue;

    /**
     * 登录账号ID
     */
    private String acntId;

    /**
     * 登录账号名称
     */
    private String acntName;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 扩展数据
     */
    private String extData;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
