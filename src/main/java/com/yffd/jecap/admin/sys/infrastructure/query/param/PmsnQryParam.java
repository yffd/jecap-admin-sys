package com.yffd.jecap.admin.sys.infrastructure.query.param;

import com.yffd.jecap.admin.base.query.BaseQryParam;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PmsnQryParam extends BaseQryParam implements Serializable {
    private static final long serialVersionUID = -5512141192518967248L;

    private String pmsnName;
    private String pmsnCode;
    private String pmsnType;
    private String pmsnStatus;
}
