package com.yffd.jecap.admin.sys.application.service;

import com.yffd.jecap.admin.sys.domain.pmsn.service.SysPmsnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class SysPmsnAppService {
    @Autowired private SysPmsnService pmsnService;

    public void removeById(String pmsnId) {
        this.pmsnService.removeById(pmsnId);
    }
}
