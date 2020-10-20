package com.yffd.jecap.admin.sys.interfaces.controller;

import com.yffd.jecap.admin.sys.application.dto.menu.MenuSaveDto;
import com.yffd.jecap.admin.sys.application.service.SysMenuAppService;
import com.yffd.jecap.admin.sys.domain.menu.service.SysMenuService;
import com.yffd.jecap.admin.base.result.RtnResult;
import com.yffd.jecap.admin.base.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "【系统-菜单】模块")
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {
    @Autowired private SysMenuService menuService;
    @Autowired private SysMenuAppService menuAppService;

    @ApiOperation(value = "树列表", consumes = "application/x-www-form-urlencoded")
    @PostMapping(value = "/listTree")
    public RtnResult listTree() {
        return RtnResult.OK(this.menuService.queryAll());
    }

    @ApiOperation(value = "详细")
    @GetMapping("/getDetail")
    public RtnResult getDetail(String menuId) {
        if (StringUtils.isBlank(menuId)) return RtnResult.FAIL("【菜单ID】不能为空");
        return RtnResult.OK(this.menuService.queryById(menuId));
    }

    @ApiOperation(value = "添加")
    @PostMapping("/add")
    public RtnResult add(@RequestBody MenuSaveDto params) {
        this.menuAppService.add(params);
        return RtnResult.OK();
    }

    @ApiOperation("修改")
    @PostMapping("/update")
    public RtnResult update(@RequestBody MenuSaveDto params) {
        this.menuAppService.update(params);
        return RtnResult.OK();
    }

    @ApiOperation(value = "删除", consumes = "application/x-www-form-urlencoded")
    @PostMapping("/delete")
    public RtnResult delete(String menuId) {
        if (StringUtils.isBlank(menuId)) return RtnResult.FAIL("【菜单ID】不能为空");
        this.menuService.deleteById(menuId);
        return RtnResult.OK();
    }

}
