package com.yffd.jecap.admin.sys.application.security.util;

import com.yffd.jecap.admin.sys.application.security.handler.ISecurityAccessHandler;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class RequestUtils {

    public static String getTokenValue(HttpServletRequest request) {
        return getTokenValue(request, ISecurityAccessHandler.ACCESS_TOKEN_KEY);
    }

    public static String getTokenValue(HttpServletRequest request, String accessTokenKey) {
        if (StringUtils.isBlank(accessTokenKey)) return null;
        String tokenValue = request.getHeader(accessTokenKey);
        if (StringUtils.isBlank(tokenValue)) {
            Cookie[] cookies = request.getCookies();
            if (null != cookies && cookies.length > 0) {
                Cookie cookie = Arrays.stream(request.getCookies()).findFirst().filter(tmp -> accessTokenKey.equals(tmp.getName())).get();
                if (null != cookie) tokenValue = cookie.getValue();
            }
        }
        if (StringUtils.isBlank(tokenValue)) {
            tokenValue = request.getParameter(accessTokenKey);
        }
        return tokenValue;
    }

}
