package com.yffd.jecap.admin.sys.domain.user.entity;

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
    @TableId(type = IdType.AUTO)
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户编号
     */
    private String userCode;

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
     * 用户状态，1=在职、2=离职
     */
    private String userStatus;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 登录账号ID
     */
    private String loginAcntId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    public SysUser(String userName, String userCode) {
        this.userName = userName;
        this.userCode = userCode;
    }

    public SysUser initValue() {
        if (StringUtils.isBlank(this.userStatus)) this.userStatus = "1";
        if (StringUtils.isBlank(this.userGender)) this.userGender = "U";
        if (StringUtils.isBlank(this.loginAcntId)) this.loginAcntId = "-1";
        if (null == this.createTime) this.createTime = LocalDateTime.now();
        return this;
    }
}
