package com.yffd.jecap.admin.sys.application.dto.user;

import com.yffd.jecap.admin.sys.domain.user.entity.SysUser;
import lombok.Data;

import java.util.Set;

@Data
public class UserSaveDTO {
    private SysUser user;
    private Set<String> roleIds;

    private String userId;
    private String loginName;
    private String loginPwd;
}
