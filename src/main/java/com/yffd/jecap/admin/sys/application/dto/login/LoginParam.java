package com.yffd.jecap.admin.sys.application.dto.login;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class LoginParam implements Serializable {
    private static final long serialVersionUID = 454465856074840551L;

    /**
     * 登录令牌
     */
    private String tokenValue;

    /** 登录账号ID */
    private String acntId;
    /** 登录账号名称 */
    private String acntName;

    /** 用户ID */
    private String userId;
    /** 用户名称 */
    private String userName;

    /** 当前登录人拥有的角色 */
    private Set<String> roles;
    /** 当前登录人拥有的权限 */
    private Set<String> pmsns;

    /**
     * 扩展参数
     */
    private Map<String, Object> params = new HashMap<>();

    /**
     * 是否为超级管理员
     * @return  true：超级管理员，false：非超级管理员
     */
    public boolean superAdmin() {
        return false;
    }

}
