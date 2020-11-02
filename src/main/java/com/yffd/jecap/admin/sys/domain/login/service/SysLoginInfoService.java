package com.yffd.jecap.admin.sys.domain.login.service;

import com.yffd.jecap.admin.sys.domain.login.entity.SysLoginInfo;
import com.yffd.jecap.admin.sys.domain.login.repo.ISysLoginInfoRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLoginInfoService {
    @Autowired private ISysLoginInfoRepo loginInfoRepo;

    public void addBy(SysLoginInfo loginInfo) {
        if (null == loginInfo) return;
        this.loginInfoRepo.addBy(loginInfo);
    }

    public SysLoginInfo queryByTokenValue(String tokenValue) {
        if (StringUtils.isBlank(tokenValue)) return null;
        SysLoginInfo entity = new SysLoginInfo();
        entity.setTokenValue(tokenValue);
        return this.loginInfoRepo.queryOne(entity);
    }

    public void removeByTokenValue(String tokenValue) {
        if (StringUtils.isBlank(tokenValue)) return;
        SysLoginInfo entity = new SysLoginInfo();
        entity.setTokenValue(tokenValue);
        this.loginInfoRepo.removeBy(entity);
    }
}
