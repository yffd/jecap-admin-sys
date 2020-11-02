package com.yffd.jecap.admin.sys.interfaces.role.model;

import lombok.Data;

import java.util.Set;

@Data
public class RoleModifyModel {
    private String roleId;
    private Set<String> addUserIds;
    private Set<String> removeUserIds;
    private Set<String> addPmsnIds;
    private Set<String> removePmsnIds;

}
