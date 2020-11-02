package com.yffd.jecap.admin.sys.domain.user.service;

import com.yffd.jecap.admin.base.page.PageData;
import com.yffd.jecap.admin.sys.domain.exception.SysException;
import com.yffd.jecap.admin.sys.domain.user.entity.SysUser;
import com.yffd.jecap.admin.sys.domain.user.repo.ISysUserRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Slf4j
@Service
public class SysUserService {
    /** 默认密码 */
    public static final String DEF_PWD = "1234qwer";

    @Autowired private ISysUserRepo userRepo;

    public String addBy(SysUser user) {
        if (null == user || StringUtils.isAnyBlank(user.getUserName(), user.getUserCode()))
            throw SysException.cast("【用户名称 | 用户编号】不能为空").prompt();
        if (null != this.queryByUserCode(user.getUserCode()))
            throw SysException.cast(String.format("用户编号已存在【%s】", user.getUserCode())).prompt();
        this.userRepo.addBy(user.initValue());
        return user.getUserId();
    }

    public void modifyById(SysUser user) {
        if (null == user || StringUtils.isBlank(user.getUserId())) return;
        //校验用户编号
        String userCode = user.getUserCode();
        if (StringUtils.isNotBlank(userCode)) {
            SysUser entity = this.queryByUserCode(userCode);
            if (null != entity && !entity.getUserId().equals(user.getUserId())) {
                throw SysException.cast(String.format("用户编号已存在【%s】", userCode)).prompt();
            }
        }
        this.userRepo.modifyById(user);
    }

    public void removeById(String userId) {
        if (StringUtils.isBlank(userId)) return;
        this.userRepo.removeById(userId);
    }

    public SysUser queryById(String userId) {
        if (StringUtils.isBlank(userId)) return null;
        return this.userRepo.queryById(userId);
    }

    public SysUser queryByUserCode(String userCode) {
        if (StringUtils.isBlank(userCode)) return null;
        return this.userRepo.queryOne(new SysUser(null, userCode));
    }

    public SysUser queryByLoginAcntId(String loginAcntId) {
        if (StringUtils.isBlank(loginAcntId)) return null;
        SysUser entity = new SysUser();
        entity.setLoginAcntId(loginAcntId);
        return this.userRepo.queryOne(entity);
    }

    public PageData<SysUser> queryPage(SysUser user, int pageNum, int pageSize) {
        return this.userRepo.queryPage(user, pageNum, pageSize);
    }

    public void disableStatus(String userId) {
        this.updateUserStatus(userId, "0");
    }

    public void enableStatus(String userId) {
        this.updateUserStatus(userId, "1");
    }

    public void updateUserStatus(String userId, String userStatus) {
        if (StringUtils.isAnyBlank(userId, userStatus)) return;
        SysUser entity = new SysUser();
        entity.setUserId(userId);
        entity.setUserStatus(userStatus);
        this.userRepo.modifyById(entity);
    }

    public Set<String> queryRltRoleCode(String userId) {
        if (StringUtils.isBlank(userId)) Collections.emptyList();
        return this.userRepo.queryRltRoleCode(userId);
    }

    public Set<String> queryRltPmsnCode(String userId) {
        if (StringUtils.isBlank(userId)) Collections.emptyList();
        return this.userRepo.queryRltPmsnCode(userId);
    }
}
