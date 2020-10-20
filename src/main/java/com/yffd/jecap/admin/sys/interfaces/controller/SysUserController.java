package com.yffd.jecap.admin.sys.interfaces.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yffd.jecap.admin.base.result.RtnResult;
import com.yffd.jecap.admin.base.web.controller.BaseController;
import com.yffd.jecap.admin.sys.application.dto.user.UserSaveDto;
import com.yffd.jecap.admin.sys.application.service.SysUserAppService;
import com.yffd.jecap.admin.sys.domain.user.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Api(tags = "【系统-用户】模块")
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {
    @Autowired private SysUserAppService userAppService;

    @ApiOperation(value = "分页", consumes = "application/x-www-form-urlencoded")
    @PostMapping(value = "/listPage")
    public RtnResult listPage(@RequestBody Map<String, Object> params) {
        int pageNum = (int) params.get("pageNum");
        int pageSize = (int) params.get("pageSize");
        JSONObject obj = (JSONObject) params.get("user");
        SysUser user = obj.toJavaObject(SysUser.class);
        if (null != obj) JSON.parseObject(params.get("user").toString(), SysUser.class);
        return this.userAppService.queryPage(user, pageNum, pageSize);
    }

    @ApiOperation(value = "详情")
    @GetMapping("/getDetail/{userId}")
    public RtnResult getDetail(@PathVariable(value = "userId") String userId) {
        if (StringUtils.isBlank(userId)) return RtnResult.FAIL("【用户ID】不能为空");
        return this.userAppService.queryById(userId);
    }

    @ApiOperation(value = "添加")
    @PostMapping("/add")
    public RtnResult add(@RequestBody UserSaveDto model) {
        return this.userAppService.add(model);
    }

    @ApiOperation("修改")
    @PostMapping("/update")
    public RtnResult update(@RequestBody UserSaveDto model) {
        return this.userAppService.update(model);
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete/{userId}")
    public RtnResult delete(@PathVariable(value = "userId") String userId) {
        if (StringUtils.isBlank(userId)) return RtnResult.FAIL("【用户ID】不能为空");
        return this.userAppService.deleteById(userId);
    }

    @ApiOperation(value = "登录")
    @ApiImplicitParam(name = "params" , paramType = "body", example = "{'loginName':'lisi', 'loginPwd':'1234qwer'}")
    @PostMapping("/login")
    public RtnResult login(@RequestBody Map<String, String> params) {
        return this.userAppService.doLogin(params.get("loginName"), params.get("loginPwd"));
    }

    @ApiOperation(value = "根据token获取当前登录信息")
    @GetMapping("/getInfo")
    public RtnResult getInfo(@Param(value = "tokenId") String tokenId) {
        return this.userAppService.queryLoginInfo(StringUtils.isNotBlank(tokenId) ? tokenId : this.getTokenId());
    }

    @ApiOperation(value = "登出")
    @PostMapping("/logout")
    public RtnResult logout(@Param(value = "tokenId") String tokenId) {
        return this.userAppService.doLogout(StringUtils.isNotBlank(tokenId) ? tokenId : this.getTokenId());
    }

    @ApiOperation("重置密码")
    @PostMapping("/resetPwd")
    public RtnResult resetPwd(String userId) {
        if (StringUtils.isBlank(userId)) return RtnResult.FAIL("【用户ID】不能为空");
        this.userAppService.resetPwd(userId, null);
        return RtnResult.OK();
    }

    @ApiOperation("启用账号")
    @PostMapping("/enable/{userId}")
    public RtnResult enable(@PathVariable(value = "userId") String userId) {
        if (StringUtils.isBlank(userId)) return RtnResult.FAIL("【用户ID】不能为空");
        this.userAppService.enable(userId);
        return RtnResult.OK();
    }

    @ApiOperation("禁用账号")
    @PostMapping("/disable/{userId}")
    public RtnResult disable(@PathVariable(value = "userId") String userId) {
        if (StringUtils.isBlank(userId)) return RtnResult.FAIL("【用户ID】不能为空");
        this.userAppService.disable(userId);
        return RtnResult.OK();
    }

    @ApiOperation(value = "删除角色")
    @ApiImplicitParam(name = "params" , paramType = "body", example = "{'userId':'string', 'roleIds':['id_1','id_2']}")
    @PostMapping("/deleteRole")
    public RtnResult deleteRole(@RequestBody JSONObject params) {
        String userId = (String) params.get("userId");
        if (StringUtils.isBlank(userId)) return RtnResult.FAIL("【用户ID】不能为空");
        JSONArray groupArray = params.getJSONArray("roleIds");
        String[] roleIds = null;
        if (null != groupArray) roleIds = groupArray.toArray(new String[groupArray.size()]);
        return this.userAppService.delUserRole(userId, Arrays.stream(roleIds).collect(Collectors.toSet()));
    }

    @ApiOperation(value = "删除用户组")
    @ApiImplicitParam(name = "params" , paramType = "body", example = "{'userId':'string', 'groupIds':['id_1','id_2']}")
    @PostMapping("/deleteGroup")
    public RtnResult deleteGroup(@RequestBody JSONObject params) {
        String userId = (String) params.get("userId");
        if (StringUtils.isBlank(userId)) return RtnResult.FAIL("【用户ID】不能为空");
        JSONArray array = params.getJSONArray("groupIds");
        String[] groupIds = null;
        if (null != array) groupIds = array.toArray(new String[array.size()]);
        return this.userAppService.delUserGroup(userId, Arrays.stream(groupIds).collect(Collectors.toSet()));
    }

    @ApiOperation(value = "删除岗位")
    @ApiImplicitParam(name = "params" , paramType = "body", example = "{'userId':'string', 'jobIds':['id_1','id_2']}")
    @PostMapping("/deleteJob")
    public RtnResult deleteJob(@RequestBody JSONObject params) {
        String userId = (String) params.get("userId");
        if (StringUtils.isBlank(userId)) return RtnResult.FAIL("【用户ID】不能为空");
        JSONArray array = params.getJSONArray("jobIds");
        String[] jobIds = null;
        if (null != array) jobIds = array.toArray(new String[array.size()]);
        return this.userAppService.delUserJob(userId, Arrays.stream(jobIds).collect(Collectors.toSet()));
    }
}
