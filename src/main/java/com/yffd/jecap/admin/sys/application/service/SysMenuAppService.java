package com.yffd.jecap.admin.sys.application.service;

import com.yffd.jecap.admin.sys.application.dto.menu.MenuSaveDto;
import com.yffd.jecap.admin.sys.domain.menu.entity.SysMenuPmsn;
import com.yffd.jecap.admin.sys.domain.menu.service.SysMenuPmsnService;
import com.yffd.jecap.admin.sys.domain.menu.service.SysMenuService;
import com.yffd.jecap.admin.sys.domain.pmsn.service.SysPmsnService;
import com.yffd.jecap.admin.base.result.RtnResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class SysMenuAppService {
    @Autowired private SysMenuService menuService;
    @Autowired private SysMenuPmsnService menuPmsnService;
    @Autowired private SysPmsnService pmsnService;

    public RtnResult add(MenuSaveDto dto) {
        if (null == dto || null == dto.getMenu()) return RtnResult.FAIL_PARAM_ISNULL();
        this.menuService.add(dto.getMenu());
        //生成权限
        if (StringUtils.isNotBlank(dto.getPmsnName())) {
            String pmsnId = this.pmsnService.add(dto.getPmsnName(), null, "1", "1");
            this.menuPmsnService.addRlt(dto.getMenu().getId(), pmsnId);
        }
        return RtnResult.OK();
    }

    public RtnResult update(MenuSaveDto dto) {
        if (null == dto || null == dto.getMenu()) return RtnResult.FAIL_PARAM_ISNULL();
        String menuId = dto.getMenu().getId();
        if (StringUtils.isBlank(menuId)) return RtnResult.FAIL("【菜单ID】不能为空");
        this.menuService.updateById(dto.getMenu());
        //更新权限
        if (StringUtils.isNotEmpty(dto.getPmsnName())) {
            SysMenuPmsn entity = this.menuPmsnService.queryByMenuId(menuId);
            this.pmsnService.updatePmsnName(entity.getPmsnId(), dto.getPmsnName());
        }
        return RtnResult.OK();
    }

    public void delete(String menuId) {
        if (StringUtils.isBlank(menuId)) return;
        this.menuService.deleteById(menuId);
        this.menuPmsnService.deleteByMenuId(menuId);//删除关联关系
    }

}
