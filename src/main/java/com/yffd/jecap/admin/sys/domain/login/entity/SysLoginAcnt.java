package com.yffd.jecap.admin.sys.domain.login.entity;

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
 * 系统-登录账户表
 * </p>
 *
 * @author ZhangST
 * @since 2020-10-28
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class SysLoginAcnt implements IBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 账号ID
     */
    @TableId(type = IdType.AUTO)
    private String acntId;

    /**
     * 账号名称
     */
    private String acntName;

    /**
     * 账号状态，1=激活、2=冻结
     */
    private String acntStatus;

    /**
     * 账号类型，1=个人账号、2=企业账号、3=连锁账号
     */
    private String acntType;

    /**
     * 账号密码
     */
    private String acntPwd;

    /**
     * 账号密码盐
     */
    private String acntPwdSalt;

    /**
     * 账号过期时间，单位=天，-1=永不过期
     */
    private Integer acntPwdExpire;

    /**
     * 账号密码修改过，1=是、0=否
     */
    private String acntPwdModified;

    /**
     * 创建类型，1=分配、2=注册
     */
    private String createType;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 登录次数
     */
    private Integer loginTimes;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    public SysLoginAcnt(String acntName, String acntPwd) {
        this.acntName = acntName;
        this.acntPwd = acntPwd;
    }

    public SysLoginAcnt initValue() {
        if (StringUtils.isBlank(this.acntStatus)) this.acntStatus = "1";
        if (StringUtils.isBlank(this.acntType)) this.acntType = "1";
        if (null == this.acntPwdExpire) this.acntPwdExpire = -1;
        if (null == this.loginTimes) this.loginTimes = 0;
        if (StringUtils.isBlank(this.acntPwdModified)) this.acntPwdModified = "0";
        if (StringUtils.isBlank(this.createType)) this.createType = "1";
        if (null == this.createTime) this.createTime = LocalDateTime.now();
        return this;
    }
}
