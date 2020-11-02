package com.yffd.jecap.admin.sys.interfaces.pmsn.controller;

import com.yffd.jecap.admin.base.result.RtnResult;
import com.yffd.jecap.admin.base.web.controller.BaseController;
import com.yffd.jecap.admin.sys.application.service.SysPmsnAppService;
import com.yffd.jecap.admin.sys.domain.pmsn.entity.SysPmsn;
import com.yffd.jecap.admin.sys.domain.pmsn.service.SysPmsnService;
import com.yffd.jecap.admin.sys.infrastructure.query.SysPmsnQry;
import com.yffd.jecap.admin.sys.infrastructure.query.param.PmsnQryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "【系统-权限】模块")
@RestController
@RequestMapping("/sys/pmsn")
public class SysPmsnController extends BaseController {
    @Autowired private SysPmsnService pmsnService;
    @Autowired private SysPmsnAppService pmsnAppService;
    @Autowired private SysPmsnQry pmsnQry;

    @ApiOperation(value = "添加")
    @PostMapping("/addBy")
    public RtnResult addBy(@RequestBody SysPmsn model) {
        this.pmsnService.addBy(model);
        return RtnResult.OK();
    }

    @ApiOperation("修改")
    @PostMapping("/modifyById")
    public RtnResult modifyById(@RequestBody SysPmsn model) {
        this.pmsnService.modifyById(model);
        return RtnResult.OK();
    }

    @ApiOperation(value = "删除")
    @PostMapping("/removeById/{pmsnId}")
    public RtnResult removeById(@PathVariable(value = "pmsnId") String pmsnId) {
        this.pmsnAppService.removeById(pmsnId);
        return RtnResult.OK();
    }

    @ApiOperation("启用权限")
    @PostMapping("/enableById/{pmsnId}")
    public RtnResult enable(@PathVariable(value = "pmsnId") String pmsnId) {
        this.pmsnService.enableStatus(pmsnId);
        return RtnResult.OK();
    }

    @ApiOperation("禁用权限")
    @PostMapping("/disableById/{pmsnId}")
    public RtnResult disableStatus(@PathVariable(value = "pmsnId") String pmsnId) {
        this.pmsnService.disableStatus(pmsnId);
        return RtnResult.OK();
    }

    @ApiOperation(value = "单条查询")
    @GetMapping("/queryById/{pmsnId}")
    public RtnResult queryById(@PathVariable(value = "pmsnId") String pmsnId) {
        return RtnResult.OK(this.pmsnService.queryById(pmsnId));
    }

    @ApiOperation(value = "列表查询-分页")
    @PostMapping(value = "/queryBy")
    public RtnResult queryBy(@RequestBody PmsnQryParam model) {
        return RtnResult.OK(this.pmsnQry.queryBy(model));
    }

}
