package com.yffd.jecap.admin.sys.domain.pmsn.service;

import com.yffd.jecap.admin.sys.domain.exception.SysException;
import com.yffd.jecap.admin.sys.domain.pmsn.entity.SysPmsn;
import com.yffd.jecap.admin.sys.domain.pmsn.repo.ISysPmsnRepo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SysPmsnService {

    @Autowired
    private ISysPmsnRepo pmsnRepo;

    /**
     * 添加权限，如果parentId为空，则认为是根节点
     * @param pmsn
     */
    public void addBy(SysPmsn pmsn) {
        if (null == pmsn) return;
        if (StringUtils.isBlank(pmsn.getParentId())) {
            this.addRoot(pmsn);
        } else {
            this.addChild(pmsn);
        }
    }

    /**
     * 添加根节点
     * @param pmsn
     */
    public void addRoot(SysPmsn pmsn) {
        if (null == pmsn || StringUtils.isAnyBlank(pmsn.getPmsnName(), pmsn.getPmsnCode()))
            throw SysException.cast("【权限名称 | 权限编号】不能为空").prompt();
        if (null != this.queryByPmsnCode(pmsn.getPmsnCode()))
            throw SysException.cast("【权限编号】已存在").prompt();
        pmsn.setParentId("-1");
        this.pmsnRepo.addBy(pmsn.initValue());
    }

    /**
     * 添加子节点
     * @param pmsn
     */
    public void addChild(SysPmsn pmsn) {
        if (null == pmsn || StringUtils.isAnyBlank(pmsn.getPmsnName(), pmsn.getPmsnCode()))
            throw SysException.cast("【权限名称 | 权限编号】不能为空").prompt();
        if (null != this.queryByPmsnCode(pmsn.getPmsnCode()))
            throw SysException.cast(String.format("【权限编号-%s】已存在", pmsn.getPmsnCode())).prompt();
        SysPmsn parent = this.pmsnRepo.queryById(pmsn.getParentId());
        if (null == parent) throw SysException.cast("【权限父ID】已不存在").prompt();
        String parentPath = parent.getParentPath();
        if (StringUtils.isBlank(parentPath)) {
            pmsn.setParentPath(parent.getPmsnId());
        } else {
            pmsn.setParentPath(parentPath + "," + parent.getPmsnId());
        }
        this.pmsnRepo.addBy(pmsn.initValue());
    }

    public void modifyById(SysPmsn pmsn) {
        if (null == pmsn || StringUtils.isBlank(pmsn.getPmsnId())) return;
        if (StringUtils.isNotBlank(pmsn.getPmsnCode())) {
            SysPmsn entity = this.queryByPmsnCode(pmsn.getPmsnCode());
            if (null != entity && !entity.getPmsnId().equals(pmsn.getPmsnId())) {
                throw SysException.cast(String.format("权限编号已存在【%s】", pmsn.getPmsnCode())).prompt();
            }
        }
        this.pmsnRepo.modifyById(pmsn);
    }

    public void modifyBatchPmsnStatus(Set<String> pmsnIds, String pmsnStatus) {
        if (CollectionUtils.isEmpty(pmsnIds) || StringUtils.isBlank(pmsnStatus)) throw SysException.paramIsEmpty();
        this.pmsnRepo.modifyBatchPmsnStatus(pmsnIds, pmsnStatus);
    }

    public void enableStatus(String pmsnId) {
        SysPmsn entity = new SysPmsn();
        entity.setPmsnId(pmsnId);
        this.pmsnRepo.modifyById(entity.enableStatus());
    }

    public void disableStatus(String pmsnId) {
        SysPmsn entity = new SysPmsn();
        entity.setPmsnId(pmsnId);
        this.pmsnRepo.modifyById(entity.disableStatus());
    }

    /**
     * 删除当前节点，以及子节点
     * @param pmsnId
     */
    public void removeById(String pmsnId) {
        if (StringUtils.isBlank(pmsnId)) return;
        this.pmsnRepo.removeById(pmsnId);
    }

    public SysPmsn queryById(String pmsnId) {
        if (StringUtils.isBlank(pmsnId)) return null;
        return this.pmsnRepo.queryById(pmsnId);
    }

    public SysPmsn queryByPmsnCode(String pmsnCode) {
        if (StringUtils.isBlank(pmsnCode)) return null;
        return this.pmsnRepo.queryOne(new SysPmsn(null, pmsnCode));
    }


    public List<SysPmsn> queryAll() {
        return this.pmsnRepo.queryAll();
    }
}
