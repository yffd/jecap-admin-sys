package com.yffd.jecap.admin.sys.application.model.menu;

import com.yffd.jecap.admin.sys.domain.menu.entity.SysMenu;
import com.yffd.jecap.admin.sys.domain.pmsn.entity.SysPmsn;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class MenuSaveDto implements Serializable {
    private static final long serialVersionUID = 676784809544257381L;

    private SysMenu menu;
    private String pmsnName;
    private String pmsnCode;

    public SysPmsn buildPmsn() {
        if (null == this.menu || StringUtils.isBlank(menu.getMenuName())) return null;
        return new SysPmsn(menu.getMenuName(), this.pmsnCode, "10");
    }
}
