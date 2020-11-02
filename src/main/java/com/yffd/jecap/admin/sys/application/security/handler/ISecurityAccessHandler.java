package com.yffd.jecap.admin.sys.application.security.handler;

import java.util.Collections;
import java.util.Set;

public interface ISecurityAccessHandler {
    String ACCESS_TOKEN_KEY = "X_ACCESS_TOKEN";

    default String getAccessTokenKey() {
        return ACCESS_TOKEN_KEY;
    }

    boolean validAccessToken(String tokenValue) throws Exception;

    default Set<String> getPmsns(String tokenValue) {
        return Collections.EMPTY_SET;
    }

    default Set<String> getRoles(String tokenValue) {
        return Collections.EMPTY_SET;
    }

}
