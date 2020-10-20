package com.yffd.jecap.admin.sys.domain.user.service;

import com.yffd.jecap.admin.sys.domain.user.entity.SysUserJob;
import com.yffd.jecap.admin.sys.domain.user.repo.ISysUserRepo;
import com.yffd.jecap.admin.sys.infrastructure.dao.user.ISysUserJobDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class SysUserJobService {
    @Autowired
    private ISysUserRepo userRepo;

    private ISysUserJobDao getDao() {
        return this.userRepo.getUserJobDao();
    }

    /**
     * 添加关系
     * @param userId
     * @param jobId
     */
    public void addRlt(String userId, String jobId) {
        if (StringUtils.isAnyBlank(userId, jobId)) return;
        SysUserJob entity = new SysUserJob(userId, jobId);
        if (null != this.getDao().queryOne(entity)) return;//已分配
        this.getDao().addBy(entity);
    }

    /**
     * 添加关系
     * @param userId
     * @param jobIds
     */
    @Transactional
    public void addRlt(String userId, Set<String> jobIds) {
        if (CollectionUtils.isEmpty(jobIds)) return;
        jobIds.forEach(jobId -> this.addRlt(userId, jobId));
    }

    /**
     * 添加关系
     * @param jobIds
     * @param userId
     */
    @Transactional
    public void addRlt(Set<String> jobIds, String userId) {
        if (CollectionUtils.isEmpty(jobIds)) return;
        jobIds.forEach(jobId -> this.addRlt(jobId, userId));
    }

    /**
     * 添加或解除关系
     * @param userId
     * @param addJobIds
     * @param delJobIds
     */
    @Transactional
    public void updateRlt(String userId, Set<String> addJobIds, Set<String> delJobIds) {
        if (StringUtils.isBlank(userId)) return;
        if (CollectionUtils.isNotEmpty(delJobIds)) delJobIds.forEach(jobId -> this.deleteBy(userId, jobId));
        if (CollectionUtils.isNotEmpty(addJobIds)) addJobIds.forEach(jobId -> this.addRlt(userId, jobId));
    }

    /**
     * 删除关系
     * @param userId
     * @param jobId
     */
    public void deleteBy(String userId, String jobId) {
        if (StringUtils.isAnyBlank(userId, jobId)) return;
        SysUserJob entity = new SysUserJob(userId, jobId);
        this.getDao().removeBy(entity);
    }

    /**
     * 删除关系
     * @param userId
     * @param jobIds
     */
    public void deleteBy(String userId, Set<String> jobIds) {
        if (StringUtils.isBlank(userId) || CollectionUtils.isEmpty(jobIds)) return;
        this.userRepo.removeJob(userId, jobIds);
    }

    /**
     * 删除关系
     * @param userId
     */
    public void deleteByUserId(String userId) {
        if (StringUtils.isBlank(userId)) return;
        this.getDao().removeBy(new SysUserJob(userId, null));
    }

    /**
     * 删除关系
     * @param jobId
     */
    public void deleteByJobId(String jobId) {
        if (StringUtils.isBlank(jobId)) return;
        this.getDao().removeBy(new SysUserJob(null, jobId));
    }

    /**
     * 查找关系
     * @param jobId
     * @return
     */
    public Set<String> queryUserIds(String jobId) {
        if (StringUtils.isBlank(jobId)) return Collections.emptySet();
        SysUserJob entity = new SysUserJob(null, jobId);
        List<SysUserJob> list = this.getDao().queryList(entity);
        if (CollectionUtils.isEmpty(list)) return Collections.emptySet();
        Set<String> ids = new HashSet<>();
        list.forEach(tmp -> ids.add(tmp.getUserId()));
        return ids;
    }

    /**
     * 查找关系
     * @param userId
     * @return
     */
    public Set<String> queryJobIds(String userId) {
        if (StringUtils.isBlank(userId)) return Collections.emptySet();
        SysUserJob entity = new SysUserJob(userId, null);
        List<SysUserJob> list = this.getDao().queryList(entity);
        if (CollectionUtils.isEmpty(list)) return Collections.emptySet();
        Set<String> ids = new HashSet<>();
        list.forEach(tmp -> ids.add(tmp.getJobId()));
        return ids;
    }


}
