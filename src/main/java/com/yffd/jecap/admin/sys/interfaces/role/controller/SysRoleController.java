package com.yffd.jecap.admin.sys.interfaces.role.controller;

import com.yffd.jecap.admin.base.result.RtnResult;
import com.yffd.jecap.admin.base.web.controller.BaseController;
import com.yffd.jecap.admin.sys.application.service.SysRoleAppService;
import com.yffd.jecap.admin.sys.domain.role.entity.SysRole;
import com.yffd.jecap.admin.sys.domain.role.service.SysRoleService;
import com.yffd.jecap.admin.sys.infrastructure.query.SysRoleQry;
import com.yffd.jecap.admin.sys.infrastructure.query.param.RoleQryParam;
import com.yffd.jecap.admin.sys.interfaces.role.model.RoleModifyModel;
import com.yffd.jecap.admin.sys.interfaces.role.model.RoleSaveModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "【系统-角色】模块")
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {
    @Autowired private SysRoleService roleService;
    @Autowired private SysRoleAppService roleAppService;
    @Autowired private SysRoleQry roleQry;

    @ApiOperation(value = "添加-关联用户")
    @PostMapping("/addWithUser")
    public RtnResult addBy(@RequestBody RoleSaveModel model) {
        this.roleAppService.addWithUser(model.getRole(), model.getUserIds());
        return RtnResult.OK();
    }

    @ApiOperation(value = "添加-关联权限")
    @PostMapping("/addWithPmsn")
    public RtnResult addWithPmsn(@RequestBody RoleSaveModel model) {
        this.roleAppService.addWithPmsn(model.getRole(), model.getPmsnIds());
        return RtnResult.OK();
    }

    @ApiOperation("修改")
    @PostMapping("/modifyById")
    public RtnResult modifyById(@RequestBody SysRole model) {
        this.roleService.modifyById(model);
        return RtnResult.OK();
    }

    @ApiOperation("修改-关联用户集")
    @PostMapping("/modifyRltUser")
    public RtnResult modifyRltUser(@RequestBody RoleModifyModel model) {
        this.roleAppService.modifyRltUser(model.getRoleId(), model.getAddUserIds(), model.getRemoveUserIds());
        return RtnResult.OK();
    }

    @ApiOperation("修改-关联权限集")
    @PostMapping("/modifyRltPmsn")
    public RtnResult modifyRltPmsn(@RequestBody RoleModifyModel model) {
        this.roleAppService.modifyRltPmsn(model.getRoleId(), model.getAddPmsnIds(), model.getRemovePmsnIds());
        return RtnResult.OK();
    }

    @ApiOperation(value = "删除")
    @PostMapping("/removeById/{roleId}")
    public RtnResult removeById(@PathVariable(value = "roleId") String roleId) {
        if (StringUtils.isBlank(roleId)) return RtnResult.FAIL("【角色ID】不能为空");
        this.roleAppService.removeById(roleId);
        return RtnResult.OK();
    }

    @ApiOperation("启用角色")
    @PostMapping("/enableById/{roleId}")
    public RtnResult enableById(@PathVariable(value = "roleId") String roleId) {
        if (StringUtils.isBlank(roleId)) return RtnResult.FAIL("【角色ID】不能为空");
        this.roleService.enableStatus(roleId);
        return RtnResult.OK();
    }

    @ApiOperation("禁用角色")
    @PostMapping("/disableById/{roleId}")
    public RtnResult disableById(@PathVariable(value = "roleId") String roleId) {
        if (StringUtils.isBlank(roleId)) return RtnResult.FAIL("【角色ID】不能为空");
        this.roleService.disableStatus(roleId);
        return RtnResult.OK();
    }

    @ApiOperation(value = "单条查询")
    @GetMapping("/queryById/{roleId}")
    public RtnResult queryById(@PathVariable(value = "roleId") String roleId) {
        if (StringUtils.isBlank(roleId)) return RtnResult.FAIL("【角色ID】不能为空");
        return RtnResult.OK(this.roleService.queryById(roleId));
    }

    @ApiOperation(value = "列表查询-分页")
    @PostMapping(value = "/queryBy")
    public RtnResult queryBy(@RequestBody RoleQryParam model) {
        return RtnResult.OK(this.roleQry.queryBy(model));
    }

    @ApiOperation(value = "查询某个用户已关联的角色-分页")
    @PostMapping(value = "/queryByRltUser")
    public RtnResult queryByRltUser(@RequestBody RoleQryParam model) {
        return RtnResult.OK(this.roleQry.queryByRltUser(model));
    }

    @ApiOperation(value = "查询某个用户未关联的角色-分页")
    @PostMapping(value = "/queryByNotRltUser")
    public RtnResult queryByNotRltUser(@RequestBody RoleQryParam model) {
        return RtnResult.OK(this.roleQry.queryByNotRltUser(model));
    }
}
