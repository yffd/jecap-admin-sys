package com.yffd.jecap.admin.sys.application.security.authc;

import com.yffd.jecap.admin.base.exception.BaseException;

public class TokenInvalidException extends BaseException {
    private static final long serialVersionUID = -689907954359500172L;

    public TokenInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public static TokenInvalidException cast() {
        TokenInvalidException ex = new TokenInvalidException("系统错误【代号：token】", null);
        ex.setPromptMsg("无效令牌");
        return ex;
    }

    public static TokenInvalidException cast(String message) {
        return new TokenInvalidException(message, null);
    }

    public static TokenInvalidException cast(Throwable cause) {
        return new TokenInvalidException(null, cause);
    }

    public static TokenInvalidException cast(Throwable cause, String message) {
        return new TokenInvalidException(message, cause);
    }

}
