package com.yffd.jecap.admin.sys.application.dto.file;

import com.yffd.jecap.admin.sys.domain.file.entity.SysFile;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class FileSaveDto implements Serializable {
    private static final long serialVersionUID = 1797362959212131160L;
    private SysFile file;
    private String pmsnName;
}
