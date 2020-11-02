package com.yffd.jecap.admin.sys.infrastructure.query.param;

import com.yffd.jecap.admin.base.query.BaseQryParam;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RoleQryParam extends BaseQryParam implements Serializable {
    private static final long serialVersionUID = -118408338811237538L;

    private String roleName;
    private String roleCode;
    private String roleStatus;

    private String userId;

}
