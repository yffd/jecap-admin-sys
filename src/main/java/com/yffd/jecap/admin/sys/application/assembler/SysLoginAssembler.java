package com.yffd.jecap.admin.sys.application.assembler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yffd.jecap.admin.sys.application.dto.login.LoginParam;
import com.yffd.jecap.admin.sys.application.security.authc.token.AccessToken;
import com.yffd.jecap.admin.sys.domain.login.entity.SysLoginAcnt;
import com.yffd.jecap.admin.sys.domain.login.entity.SysLoginInfo;
import com.yffd.jecap.admin.sys.domain.user.entity.SysUser;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SysLoginAssembler {

    public static LoginParam buildLoginParam(AccessToken accessToken, SysLoginAcnt loginAcnt, SysUser user) {
        LoginParam param = new LoginParam();
        if (null != accessToken) {
            param.setTokenValue(accessToken.getTokenValue());
        }
        if (null != loginAcnt) {
            param.setAcntId(loginAcnt.getAcntId()).setAcntName(loginAcnt.getAcntName());
        }
        if (null != user) {
            param.setUserId(user.getUserId()).setUserName(user.getUserName());
        }
        return param;
    }

    public static LoginParam buildLoginParam(SysLoginInfo info) {
        if (null == info) return null;
        LoginParam param = new LoginParam();
        param.setTokenValue(info.getTokenValue())
        .setAcntId(info.getAcntId())
        .setAcntName(info.getAcntName())
        .setUserId(info.getUserId())
        .setUserName(info.getUserName());
        String extData = info.getExtData();
        JSONObject obj = JSON.parseObject(extData);

        Object params = obj.get("params");
        if (null != params) {
            JSONObject tmp = (JSONObject) params;
            tmp.forEach((key, value) -> param.getParams().put(key, value));
        }

        Set<String> pmsnCodes = new HashSet<>();
        Set<String> roleCodes = new HashSet<>();

        JSONArray pmsnCodeArr = (JSONArray) obj.get("pmsnCode");
        if (null != pmsnCodeArr)
            Arrays.stream(pmsnCodeArr.toArray()).forEach(tmp -> pmsnCodes.add((String) tmp));

        JSONArray roleCodeArr = (JSONArray) obj.get("roleCode");
        if (null != roleCodeArr)
            Arrays.stream(roleCodeArr.toArray()).forEach(tmp -> roleCodes.add((String) tmp));

        param.setPmsns(pmsnCodes);
        param.setRoles(roleCodes);
        return param;
    }

    public static SysLoginInfo buildLoginInfo(LoginParam param) {
        if (null == param) return null;
        SysLoginInfo info = new SysLoginInfo();
        info.setTokenValue(param.getTokenValue())
        .setAcntId(param.getAcntId())
        .setAcntName(param.getAcntName())
        .setUserId(param.getUserId())
        .setUserName(param.getUserName())
        .setCreateTime(LocalDateTime.now());
        JSONObject obj = new JSONObject();
        obj.put("pmsnCode", param.getPmsns());
        obj.put("roleCode", param.getRoles());
        obj.put("params", param.getParams());
        info.setExtData(obj.toJSONString());
        return info;
    }


}
