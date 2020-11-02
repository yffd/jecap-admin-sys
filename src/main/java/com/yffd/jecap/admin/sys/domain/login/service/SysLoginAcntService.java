package com.yffd.jecap.admin.sys.domain.login.service;

import com.yffd.jecap.admin.sys.domain.exception.SysException;
import com.yffd.jecap.admin.sys.domain.login.entity.SysLoginAcnt;
import com.yffd.jecap.admin.sys.domain.login.repo.ISysLoginAcntRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

@Slf4j
@Service
public class SysLoginAcntService {
    /** 账户账户状态，激活 */
    public static final String ACNT_STATUS_ACTIVE = "1";
    /** 账户账户状态，冻结 */
    public static final String ACNT_STATUS_INACTIVE = "0";

    @Autowired private ISysLoginAcntRepo loginAcntRepo;

    public String addBy(SysLoginAcnt loginAcnt) {
        if (null == loginAcnt || StringUtils.isAnyBlank(loginAcnt.getAcntName(), loginAcnt.getAcntPwd()))
            throw SysException.cast("【账号 | 密码】不能为空").prompt();
        if (null != this.queryByAcntName(loginAcnt.getAcntName()))
            throw SysException.cast(String.format("账号已存在【%s】", loginAcnt.getAcntName())).prompt();
        this.encrtpyLoginAcnt(loginAcnt);//加密登录账号
        this.loginAcntRepo.addBy(loginAcnt.initValue());
        return loginAcnt.getAcntId();
    }

    public void modifyById(SysLoginAcnt loginAcnt) {
        if (null == loginAcnt || StringUtils.isBlank(loginAcnt.getAcntId())) return;
        if (StringUtils.isNotBlank(loginAcnt.getAcntName())) {
            SysLoginAcnt entity = this.queryByAcntName(loginAcnt.getAcntName());
            if (null != entity && !entity.getAcntId().equals(loginAcnt.getAcntId())) {
                throw SysException.cast(String.format("账号已存在【%s】", loginAcnt.getAcntId())).prompt();
            }
        }
        this.loginAcntRepo.modifyById(loginAcnt);
    }

    /**
     * 修改登录账号状态
     * @param loginAcntId
     * @param acntStatus
     */
    private void modifyLoginAcntStatus(String loginAcntId, String acntStatus) {
        if (StringUtils.isAnyBlank(loginAcntId, acntStatus)) throw SysException.paramIsEmpty();
        SysLoginAcnt entity = new SysLoginAcnt();
        entity.setAcntId(loginAcntId);
        entity.setAcntStatus(acntStatus);
        this.loginAcntRepo.modifyById(entity);
    }

    /**
     * 激活账号
     * @param loginAcntId
     */
    public void enableLoginAcntStatus(String loginAcntId) {
        this.modifyLoginAcntStatus(loginAcntId, ACNT_STATUS_ACTIVE);
    }

    /**
     * 禁用账号
     * @param loginAcntId
     */
    public void disableStatus(String loginAcntId) {
        this.modifyLoginAcntStatus(loginAcntId, ACNT_STATUS_INACTIVE);
    }

    /**
     * 删除账号
     * @param loginAcntId
     */
    public void removeById(String loginAcntId) {
        if (StringUtils.isBlank(loginAcntId)) return;
        this.loginAcntRepo.removeById(loginAcntId);
    }

    /**
     * 查询账号
     * @param loginAcntId
     * @return
     */
    public SysLoginAcnt queryById(String loginAcntId) {
        if (StringUtils.isBlank(loginAcntId)) return null;
        return this.loginAcntRepo.queryById(loginAcntId);
    }

    /**
     * 根据账号名称查询
     * @param acntName
     * @return
     */
    public SysLoginAcnt queryByAcntName(String acntName) {
        if (StringUtils.isBlank(acntName)) return null;
        return this.loginAcntRepo.queryOne(new SysLoginAcnt(acntName, null));
    }

    /**
     * 校验密码
     * @param acntPwd
     * @param encryptSalt
     * @param encryptPwd
     * @return
     */
    public boolean validPwd(String acntPwd, String encryptSalt, String encryptPwd) {
        if (StringUtils.isAnyBlank(acntPwd, encryptSalt, encryptPwd)) return false;
        return encryptPwd.equals(this.encryptPwd(acntPwd, encryptSalt));
    }

    /**
     * 加密登录账号
     * @param loginAcnt
     */
    public void encrtpyLoginAcnt(SysLoginAcnt loginAcnt) {
        if (null == loginAcnt || StringUtils.isAnyBlank(loginAcnt.getAcntName(), loginAcnt.getAcntPwd()))
            throw SysException.cast("登录账号加密失败").prompt();
        String salt = loginAcnt.getAcntName() + UUID.randomUUID().toString().replaceAll("-", "");
        String encryptPwd = this.encryptPwd(loginAcnt.getAcntPwd(), salt);
        loginAcnt.setAcntPwd(encryptPwd);
        loginAcnt.setAcntPwdSalt(salt);
    }

    private String encryptPwd(String acntPwd, String encryptSalt) {
        if (StringUtils.isAnyBlank(acntPwd, encryptSalt)) return null;
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            log.error("密码加密失败", e);
            return null;
        }
        digest.reset();
        digest.update(encryptSalt.getBytes());
        byte[] hashedBytes = digest.digest(acntPwd.getBytes());
        int iterations = 2;
        for (int i=0; i< iterations; i++) {
            digest.reset();
            hashedBytes = digest.digest(hashedBytes);
        }
        return Base64.getEncoder().encodeToString(hashedBytes);
    }


}
