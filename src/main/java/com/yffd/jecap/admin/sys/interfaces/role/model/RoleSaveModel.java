package com.yffd.jecap.admin.sys.interfaces.role.model;

import com.yffd.jecap.admin.sys.domain.role.entity.SysRole;
import lombok.Data;

import java.util.Set;

@Data
public class RoleSaveModel {
    private SysRole role;
    private Set<String> userIds;
    private Set<String> pmsnIds;

}
