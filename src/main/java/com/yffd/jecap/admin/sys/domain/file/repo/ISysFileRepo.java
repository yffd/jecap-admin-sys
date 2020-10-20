package com.yffd.jecap.admin.sys.domain.file.repo;

import com.yffd.jecap.admin.sys.domain.file.entity.SysFile;
import com.yffd.jecap.admin.sys.domain.file.entity.SysFilePmsn;
import com.yffd.jecap.admin.base.dao.IBaseDao;

public interface ISysFileRepo {

    IBaseDao<SysFile> getFileDao();
    IBaseDao<SysFilePmsn> getFilePmsnDao();
}
