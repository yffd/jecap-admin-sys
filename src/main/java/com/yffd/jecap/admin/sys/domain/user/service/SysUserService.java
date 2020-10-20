package com.yffd.jecap.admin.sys.domain.user.service;

import com.yffd.jecap.admin.base.exception.DataExistException;
import com.yffd.jecap.admin.base.page.PageData;
import com.yffd.jecap.admin.sys.domain.exception.SysException;
import com.yffd.jecap.admin.sys.domain.role.entity.SysRole;
import com.yffd.jecap.admin.sys.domain.role.service.SysRoleService;
import com.yffd.jecap.admin.sys.domain.user.entity.SysUser;
import com.yffd.jecap.admin.sys.domain.user.repo.ISysUserRepo;
import com.yffd.jecap.admin.sys.infrastructure.dao.user.ISysUserDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

@Slf4j
@Service
public class SysUserService {
    /** 默认密码 */
    public static final String DEF_PWD = "1234qwer";
    /** 账号类型，1=个人账号、2=企业账号、3=连锁账号 */
    public static final String ACNT_TYPE_PERSONAL = "1";
    /** 账号类型，1=个人账号、2=企业账号、3=连锁账号 */
    public static final String ACNT_TYPE_COMPANY = "2";
    /** 账号类型，1=个人账号、2=企业账号、3=连锁账号 */
    public static final String ACNT_TYPE_CHAIN_HOSPTITAL = "3";

    @Autowired
    private SysRoleService userService;
    @Autowired private SysUserRoleService userRoleService;
    @Autowired private SysUserJobService userJobService;
    @Autowired private SysUserGroupService userGroupService;

    @Autowired private ISysUserRepo userRepo;

    private ISysUserDao getDao() {
        return this.userRepo.getUserDao();
    }

    /**
     * 添加
     * @param user
     */
    public void add(SysUser user) {
        if (null == user || StringUtils.isBlank(user.getAcntName())) throw SysException.cast("【账号名称】不能为空").prompt();
        if (this.existByAcntName(user.getAcntName())) throw DataExistException.cast("【账号名称】已存在");
        if (StringUtils.isBlank(user.getAcntType())) user.setAcntType(ACNT_TYPE_PERSONAL);
        if (StringUtils.isBlank(user.getUserName())) user.setUserName(user.getAcntName());
        if (StringUtils.isNotBlank(user.getAcntPwd())) this.encryptUser(user);
        this.getDao().addBy(user);
    }

    /**
     * 修改
     * @param user
     */
    public void updateById(SysUser user) {
        if (null == user || StringUtils.isBlank(user.getId())) return;
        this.getDao().modifyById(user);
    }

    /**
     * 删除
     * @param userId
     */
    @Transactional
    public void deleteById(String userId) {
        if (StringUtils.isBlank(userId)) return;
        this.getDao().deleteById(userId);
        //删除关系
        this.userRoleService.deleteByUserId(userId);
        this.userGroupService.delByUserId(userId);
        this.userJobService.deleteByUserId(userId);
    }

    /**
     * 详细
     * @param userId
     * @return
     */
    public SysUser queryById(String userId) {
        if (StringUtils.isBlank(userId)) return null;
        return this.getDao().queryById(userId);
    }

    /**
     * 分页
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageData<SysUser> queryPage(SysUser user, int pageNum, int pageSize) {
        return this.getDao().queryPage(user, pageNum, pageSize);
    }

    /**
     * 根据用户名称查询
     * @param acntName
     * @return
     */
    public SysUser queryByAcntName(String acntName) {
        if (StringUtils.isBlank(acntName)) return null;
        SysUser entity = new SysUser();
        entity.setAcntName(acntName);
        return this.getDao().queryOne(entity);
    }

    /**
     * 校验账户名称是否已存在
     * @param acntName
     * @return
     */
    public boolean existByAcntName(String acntName) {
        return null != this.queryByAcntName(acntName);
    }

    /**
     * 禁用账户
     * @param userId
     */
    public void disableUser(String userId) {
        if (StringUtils.isBlank(userId)) return;
        SysUser entity = new SysUser();
        entity.setId(userId);
        entity.setAcntStatus("0");
        this.getDao().modifyById(entity);
    }

    /**
     * 启用账户
     * @param userId
     */
    public void enableUser(String userId) {
        if (StringUtils.isBlank(userId)) return;
        SysUser entity = new SysUser();
        entity.setId(userId);
        entity.setAcntStatus("1");
        this.getDao().modifyById(entity);
    }

    public void encryptUser(SysUser entity) {
        if (null == entity) return;
        String salt = entity.getAcntName() + UUID.randomUUID().toString().replaceAll("-", "");
        String encryptPwd = this.encryptPwd(entity.getAcntPwd(), salt);
        entity.setAcntPwd(encryptPwd);
        entity.setAcntPwdSalt(salt);
        entity.setAcntPwdExpire(-1);
    }

    public String encryptPwd(String password, String salt) {
        if (StringUtils.isAnyBlank(password, salt)) return null;
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            log.error("密码加密失败", e);
            return null;
        }
        if (StringUtils.isNotBlank(salt)) {
            digest.reset();
            digest.update(salt.getBytes());
        }
        byte[] hashedBytes = digest.digest(password.getBytes());
        int iterations = 2;
        for (int i=0; i< iterations; i++) {
            digest.reset();
            hashedBytes = digest.digest(hashedBytes);
        }
        return Base64.getEncoder().encodeToString(hashedBytes);
    }

    public boolean checkPwd(String acntPwd, String encryptPwd, String salt) {
        if (StringUtils.isBlank(encryptPwd)) {
            // 如果密码密文为空，说明是默认密码，默认密码为明文，直接比较即可
            return DEF_PWD.equals(acntPwd);
        }
        return encryptPwd.equals(this.encryptPwd(acntPwd, salt));
    }

    /**
     * 修改密码，如果新密码为空，则修改为默认密码
     * @param acntName
     * @param newAcntPwd
     */
    public void updatePwd(String acntName, String newAcntPwd) {
        if (StringUtils.isBlank(acntName)) throw SysException.cast("账号名称不能为空").prompt();
        SysUser user = new SysUser();
        if (StringUtils.isBlank(newAcntPwd)) {
            user.setAcntPwd("");
            user.setAcntPwdSalt("");
        } else {
            user.setAcntName(acntName);
            user.setAcntPwd(newAcntPwd);
            this.encryptUser(user);
            user.setAcntName(null);
        }

        SysUser old = new SysUser();
        old.setAcntName(acntName);
        this.getDao().modifyBy(old, user);
    }

    public void rltRole(String userId, String roleCode) {
        if (StringUtils.isAnyBlank(userId, roleCode)) return;
        SysRole role = this.userService.queryByRoleCode(roleCode);
        if (null == role) {
            throw SysException.cast(String.format("角色编号不存在【%s】", roleCode)).prompt();
        } else {
            this.userRoleService.addRlt(userId, role.getId());
        }
    }

}
