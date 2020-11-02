package com.yffd.jecap.admin.sys.application.dto.user;

import lombok.Data;

import java.util.Set;

@Data
public class UserModifyDTO {
    private String userId;
    private Set<String> addRoleIds;
    private Set<String> removeRoleIds;

}
