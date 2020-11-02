package com.yffd.jecap.admin.sys.application.security.authz;

import com.yffd.jecap.admin.base.exception.BaseException;

public class AuthzInvalidException extends BaseException {
    private static final long serialVersionUID = -689907954359500172L;

    public AuthzInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public static AuthzInvalidException cast() {
        AuthzInvalidException ex = new AuthzInvalidException("系统错误【代号：authz】", null);
        ex.setPromptMsg("非法访问");
        return ex;
    }

    public static AuthzInvalidException cast(String message) {
        return new AuthzInvalidException(message, null);
    }

    public static AuthzInvalidException cast(Throwable cause) {
        return new AuthzInvalidException(null, cause);
    }

    public static AuthzInvalidException cast(Throwable cause, String message) {
        return new AuthzInvalidException(message, cause);
    }

}
