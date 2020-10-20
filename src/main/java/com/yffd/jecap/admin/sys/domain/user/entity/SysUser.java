package com.yffd.jecap.admin.sys.domain.user.entity;

import com.yffd.jecap.admin.base.entity.IBaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 * 系统-用户表
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class SysUser implements IBaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户电话
     */
    private String userPhone;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户性别，男：M、女：F、未知：U
     */
    private String userGender;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 账号名称
     */
    private String acntName;

    /**
     * 账号类型，1=个人账号、2=企业账号、3=连锁账号
     */
    private String acntType;

    /**
     * 账号状态，1=启用、0=禁用
     */
    private String acntStatus;

    /**
     * 账号密码
     */
    private String acntPwd;

    /**
     * 账号密码盐
     */
    private String acntPwdSalt;

    /**
     * 账号密码过期时间，单位=天，-1=永不过期
     */
    private Integer acntPwdExpire;

    /**
     * 登录令牌值
     */
    private String loginTokenValue;

    /**
     * 登录令牌值过期时间，单位=秒，-1=永不过期
     */
    private Integer loginTokenExpire;

    /**
     * 最后登录IP
     */
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    public SysUser(String acntName, String acntPwd) {
        this.acntName = acntName;
        this.acntPwd = acntPwd;
        if (StringUtils.isNotBlank(acntPwd))
            this.acntPwdSalt = acntName + UUID.randomUUID().toString().replace("-", "");
        this.acntStatus = "1";
        this.acntType = "1";
    }

    public SysUser(String acntName, String acntPwd, String acntType) {
        this.acntName = acntName;
        this.acntType = acntType;
        this.acntPwd = acntPwd;
        if (StringUtils.isNotBlank(acntPwd))
            this.acntPwdSalt = acntName + UUID.randomUUID().toString().replace("-", "");
        this.acntStatus = "1";
    }

}
