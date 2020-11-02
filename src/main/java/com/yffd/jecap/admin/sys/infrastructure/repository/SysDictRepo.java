package com.yffd.jecap.admin.sys.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yffd.jecap.admin.base.dao.IBaseDao;
import com.yffd.jecap.admin.sys.domain.dict.entity.SysDict;
import com.yffd.jecap.admin.sys.domain.dict.entity.SysDictProps;
import com.yffd.jecap.admin.sys.domain.dict.repo.ISysDictRepo;
import com.yffd.jecap.admin.sys.infrastructure.dao.ISysDictDao;
import com.yffd.jecap.admin.sys.infrastructure.dao.ISysDictPropsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SysDictRepo implements ISysDictRepo {
    @Autowired
    private ISysDictDao dictDao;
    @Autowired private ISysDictPropsDao dictPropsDao;

    @Override
    public IBaseDao<SysDict> getDictDao() {
        return dictDao;
    }

    @Override
    public IBaseDao<SysDictProps> getDictPropsDao() {
        return dictPropsDao;
    }

    @Override
    public void removeByPath(String pathPrefix) {
        this.dictDao.delete(new QueryWrapper<SysDict>().likeRight("path", pathPrefix));
    }
}
