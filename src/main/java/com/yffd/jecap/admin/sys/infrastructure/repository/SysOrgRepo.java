package com.yffd.jecap.admin.sys.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yffd.jecap.admin.sys.domain.org.entity.SysOrg;
import com.yffd.jecap.admin.sys.domain.org.repo.ISysOrgRepo;
import com.yffd.jecap.admin.sys.infrastructure.dao.org.ISysOrgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SysOrgRepo implements ISysOrgRepo {
    @Autowired
    private ISysOrgDao orgDao;

    @Override
    public ISysOrgDao getOrgDao() {
        return orgDao;
    }

    @Override
    public void removeByPath(String pathPrefix) {
        this.orgDao.delete(new QueryWrapper<SysOrg>().likeRight("path", pathPrefix));
    }

}
