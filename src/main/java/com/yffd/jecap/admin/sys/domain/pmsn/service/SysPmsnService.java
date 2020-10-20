package com.yffd.jecap.admin.sys.domain.pmsn.service;

import com.yffd.jecap.admin.base.dao.IBaseDao;
import com.yffd.jecap.admin.base.page.PageData;
import com.yffd.jecap.admin.sys.domain.exception.SysException;
import com.yffd.jecap.admin.sys.domain.pmsn.entity.SysPmsn;
import com.yffd.jecap.admin.sys.domain.pmsn.repo.ISysPmsnRepo;
import com.yffd.jecap.admin.sys.domain.role.entity.SysRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysPmsnService {
    @Autowired
    private ISysPmsnRepo pmsnRepo;

    private IBaseDao<SysPmsn> getDao() {
        return this.pmsnRepo.getPmsnDao();
    }

    public void add(SysPmsn pmsn) {
        if (null == pmsn) return;
        if (StringUtils.isAnyBlank(pmsn.getPmsnName(), pmsn.getPmsnType())) throw SysException.cast("【权限名称 | 权限类型】不能为空").prompt();
        if (StringUtils.isNotBlank(pmsn.getPmsnCode()) && null != this.queryByPmsnCode(pmsn.getPmsnCode())) {
            throw SysException.cast(String.format("角色编号已存在【%s】", pmsn.getPmsnCode())).prompt();
        }
        if (StringUtils.isBlank(pmsn.getPmsnStatus())) pmsn.setPmsnStatus("1");
        this.getDao().addBy(pmsn);
    }

    public String add(String pmsnName, String pmsnCode, String pmsnType, String pmsnStatus) {
        SysPmsn pmsn = new SysPmsn(pmsnName, pmsnCode, pmsnType, pmsnStatus);
        this.add(pmsn);
        return pmsn.getId();
    }

    public void updateById(SysPmsn pmsn) {
        if (null == pmsn || StringUtils.isBlank(pmsn.getId())) return;
        this.getDao().modifyById(pmsn);
    }

    public void deleteById(String pmsnId) {
        if (StringUtils.isBlank(pmsnId)) return;
        this.getDao().removeById(pmsnId);
    }

    public SysPmsn queryById(String pmsnId) {
        if (StringUtils.isBlank(pmsnId)) return null;
        return this.getDao().queryById(pmsnId);
    }

    public SysPmsn queryByPmsnCode(String pmsnCode) {
        if (StringUtils.isBlank(pmsnCode)) return null;
        return this.getDao().queryOne(new SysPmsn(null, pmsnCode));
    }

    public PageData<SysPmsn> queryPage(SysPmsn pmsn, int pageNum, int pageSize) {
        return this.getDao().queryPage(pmsn, pageNum, pageSize);
    }

    public void updatePmsnName(String pmsnId, String pmsnName) {
        if (StringUtils.isBlank(pmsnId)) return;
        if (null == pmsnName) pmsnName = "";
        SysPmsn entity = new SysPmsn(pmsnName, null, null);
        entity.setId(pmsnId);
        this.getDao().modifyById(entity);
    }

    public void enable(String pmsnId) {
        this.updateStatus(pmsnId, "1");
    }

    public void disable(String pmsnId) {
        this.updateStatus(pmsnId, "0");
    }

    public void updateStatus(String pmsnId, String pmsnStatus) {
        if (StringUtils.isAnyBlank(pmsnId, pmsnStatus)) return;
        SysPmsn entity = new SysPmsn();
        entity.setId(pmsnId);
        entity.setPmsnStatus(pmsnStatus);
        this.getDao().modifyById(entity);
    }
}
