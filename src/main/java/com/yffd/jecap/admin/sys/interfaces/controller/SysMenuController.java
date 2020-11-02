package com.yffd.jecap.admin.sys.interfaces.controller;

import com.yffd.jecap.admin.base.result.RtnResult;
import com.yffd.jecap.admin.base.web.controller.BaseController;
import com.yffd.jecap.admin.sys.application.model.menu.MenuSaveDto;
import com.yffd.jecap.admin.sys.application.service.SysMenuAppService;
import com.yffd.jecap.admin.sys.domain.menu.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "【系统-菜单】模块")
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {
    @Autowired private SysMenuService menuService;
    @Autowired private SysMenuAppService menuAppService;

    @ApiOperation(value = "列表查询-分页")
    @PostMapping(value = "/queryBy")
    public RtnResult queryList() {
        return RtnResult.OK(this.menuService.queryAll());
    }

    @ApiOperation(value = "树查询")
    @PostMapping(value = "/queryTree")
    public RtnResult queryTree() {
        return RtnResult.OK(this.menuAppService.queryTree());
    }

    @ApiOperation(value = "单条查询")
    @GetMapping("/queryById/{menuId}")
    public RtnResult queryDetail(@PathVariable("menuId") String menuId) {
        return RtnResult.OK(this.menuService.queryById(menuId));
    }

    @ApiOperation(value = "添加")
    @PostMapping("/addBy")
    public RtnResult addBy(@RequestBody MenuSaveDto params) {
        this.menuAppService.addBy(params);
        return RtnResult.OK();
    }

    @ApiOperation("修改")
    @PostMapping("/modifyById")
    public RtnResult modifyById(@RequestBody MenuSaveDto params) {
        this.menuAppService.modifyById(params);
        return RtnResult.OK();
    }

    @ApiOperation(value = "删除")
    @PostMapping("/removeById/{menuId}")
    public RtnResult removeById(@PathVariable("menuId") String menuId) {
        this.menuAppService.removeByIdWithChildren(menuId);
        return RtnResult.OK();
    }

}
