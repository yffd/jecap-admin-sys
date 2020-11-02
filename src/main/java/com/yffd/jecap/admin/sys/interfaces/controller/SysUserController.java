package com.yffd.jecap.admin.sys.interfaces.controller;

import com.yffd.jecap.admin.base.result.RtnResult;
import com.yffd.jecap.admin.base.web.controller.BaseController;
import com.yffd.jecap.admin.sys.application.dto.user.UserModifyDTO;
import com.yffd.jecap.admin.sys.application.dto.user.UserSaveDTO;
import com.yffd.jecap.admin.sys.application.security.authz.annotation.RequiresPmsns;
import com.yffd.jecap.admin.sys.application.service.SysUserAppService;
import com.yffd.jecap.admin.sys.domain.user.entity.SysUser;
import com.yffd.jecap.admin.sys.domain.user.service.SysUserService;
import com.yffd.jecap.admin.sys.infrastructure.query.SysUserQry;
import com.yffd.jecap.admin.sys.infrastructure.query.param.UserQryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "【系统-用户】模块")
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {
    @Autowired private SysUserService userService;
    @Autowired private SysUserAppService userAppService;
    @Autowired private SysUserQry userQry;


    @RequiresPmsns({"pmsn-0", "pmsn-0-1"})
    @ApiOperation(value = "添加")
    @PostMapping("/addBy")
    public RtnResult addBy(@RequestBody UserSaveDTO model) {
        this.userAppService.addBy(model);
        return RtnResult.OK();
    }

    @ApiOperation("修改")
    @PostMapping("/modifyById")
    public RtnResult modifyById(@RequestBody UserModifyDTO model) {
        this.userAppService.modifyById(model);
        return RtnResult.OK();
    }

    @ApiOperation(value = "删除")
    @PostMapping("/removeById/{userId}")
    public RtnResult removeById(@PathVariable(value = "userId") String userId) {
        this.userService.removeById(userId);
        return RtnResult.OK();
    }

    @ApiOperation("启用")
    @PostMapping("/enableById/{userId}")
    public RtnResult enableById(@PathVariable(value = "userId") String userId) {
        if (StringUtils.isBlank(userId)) return RtnResult.FAIL("【用户ID】不能为空");
        this.userService.enableStatus(userId);
        return RtnResult.OK();
    }

    @ApiOperation("禁用")
    @PostMapping("/disableById/{userId}")
    public RtnResult disableById(@PathVariable(value = "userId") String userId) {
        if (StringUtils.isBlank(userId)) return RtnResult.FAIL("【用户ID】不能为空");
        this.userService.disableStatus(userId);
        return RtnResult.OK();
    }

    @ApiOperation(value = "查询某个角色已关联的用户-分页")
    @PostMapping(value = "/queryByRltRole")
    public RtnResult queryByRltRole(@RequestBody UserQryParam model) {
        return RtnResult.OK(this.userQry.queryByRltRole(model));
    }

    @ApiOperation(value = "查询某个角色未关联的用户-分页")
    @PostMapping(value = "/queryByNotRltRole")
    public RtnResult queryNotRltRole(@RequestBody UserQryParam model) {
        return RtnResult.OK(this.userQry.queryByNotRltRole(model));
    }

    @ApiOperation(value = "单条查询")
    @GetMapping("/queryById/{userId}")
    public RtnResult queryById(@PathVariable(value = "userId") String userId) {
        if (StringUtils.isBlank(userId)) return RtnResult.FAIL("【用户ID】不能为空");
        return RtnResult.OK(this.userService.queryById(userId));
    }

    @RequiresPmsns({"pmsn-0000"})
    @ApiOperation(value = "列表查询-分页")
    @PostMapping(value = "/queryBy")
    public RtnResult queryBy(@RequestBody UserQryParam model) {
        return RtnResult.OK(this.userQry.queryBy(model));
    }



//    @ApiOperation(value = "登录")
//    @ApiImplicitParam(name = "params" , paramType = "body", example = "{'loginName':'lisi', 'loginPwd':'1234qwer'}")
//    @PostMapping("/login")
//    public RtnResult login(@RequestBody Map<String, String> params) {
//        return this.userAppService.doLogin(params.get("loginName"), params.get("loginPwd"));
//    }
//
//    @ApiOperation(value = "根据token获取当前登录信息")
//    @GetMapping("/getInfo")
//    public RtnResult getInfo(@Param(value = "tokenId") String tokenId) {
//        return this.userAppService.queryLoginInfo(StringUtils.isNotBlank(tokenId) ? tokenId : this.getTokenId());
//    }
//
//    @ApiOperation(value = "登出")
//    @PostMapping("/logout")
//    public RtnResult logout(@Param(value = "tokenId") String tokenId) {
//        return this.userAppService.doLogout(StringUtils.isNotBlank(tokenId) ? tokenId : this.getTokenId());
//    }
//
//    @ApiOperation("重置密码")
//    @PostMapping("/resetPwd")
//    public RtnResult resetPwd(String userId) {
//        if (StringUtils.isBlank(userId)) return RtnResult.FAIL("【用户ID】不能为空");
//        this.userAppService.resetPwd(userId, null);
//        return RtnResult.OK();
//    }

//    @ApiOperation(value = "删除角色")
//    @ApiImplicitParam(name = "params" , paramType = "body", example = "{'userId':'string', 'roleIds':['id_1','id_2']}")
//    @PostMapping("/deleteRole")
//    public RtnResult deleteRole(@RequestBody JSONObject params) {
//        String userId = (String) params.get("userId");
//        if (StringUtils.isBlank(userId)) return RtnResult.FAIL("【用户ID】不能为空");
//        JSONArray groupArray = params.getJSONArray("roleIds");
//        String[] roleIds = null;
//        if (null != groupArray) roleIds = groupArray.toArray(new String[groupArray.size()]);
//        return this.userService.delUserRole(userId, Arrays.stream(roleIds).collect(Collectors.toSet()));
//    }
//
//    @ApiOperation(value = "删除用户组")
//    @ApiImplicitParam(name = "params" , paramType = "body", example = "{'userId':'string', 'groupIds':['id_1','id_2']}")
//    @PostMapping("/deleteGroup")
//    public RtnResult deleteGroup(@RequestBody JSONObject params) {
//        String userId = (String) params.get("userId");
//        if (StringUtils.isBlank(userId)) return RtnResult.FAIL("【用户ID】不能为空");
//        JSONArray array = params.getJSONArray("groupIds");
//        String[] groupIds = null;
//        if (null != array) groupIds = array.toArray(new String[array.size()]);
//        return this.userService.delUserGroup(userId, Arrays.stream(groupIds).collect(Collectors.toSet()));
//    }
//
//    @ApiOperation(value = "删除岗位")
//    @ApiImplicitParam(name = "params" , paramType = "body", example = "{'userId':'string', 'jobIds':['id_1','id_2']}")
//    @PostMapping("/deleteJob")
//    public RtnResult deleteJob(@RequestBody JSONObject params) {
//        String userId = (String) params.get("userId");
//        if (StringUtils.isBlank(userId)) return RtnResult.FAIL("【用户ID】不能为空");
//        JSONArray array = params.getJSONArray("jobIds");
//        String[] jobIds = null;
//        if (null != array) jobIds = array.toArray(new String[array.size()]);
//        return this.userService.delUserJob(userId, Arrays.stream(jobIds).collect(Collectors.toSet()));
//    }
}
