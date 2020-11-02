package com.yffd.jecap.admin.sys.application.dto.login;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
public class LoginToken implements Serializable {
    private static final long serialVersionUID = 454465856074840551L;

    /** 登录名称 */
    private String loginName;
    /** 登录密码 */
    private String loginPwd;

    public LoginToken(String loginName, String loginPwd) {
        this.loginName = loginName;
        this.loginPwd = loginPwd;
    }
}
