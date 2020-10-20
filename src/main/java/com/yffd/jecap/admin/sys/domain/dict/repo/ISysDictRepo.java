package com.yffd.jecap.admin.sys.domain.dict.repo;

import com.yffd.jecap.admin.base.dao.IBaseDao;
import com.yffd.jecap.admin.sys.domain.dict.entity.SysDict;
import com.yffd.jecap.admin.sys.domain.dict.entity.SysDictProps;

public interface ISysDictRepo {

    IBaseDao<SysDict> getDictDao();
    IBaseDao<SysDictProps> getDictPropsDao();

    void removeByPath(String pathPrefix);
}
