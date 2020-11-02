package com.yffd.jecap.admin.sys.application.security.authc.token;

import java.io.Serializable;
import java.util.UUID;

public class AccessToken implements IAccessToken, Serializable {
    private static final long serialVersionUID = 3590279246509048723L;
    private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    /** token 值 */
    private String tokenValue;
    /** token 过期时间，如果为 -1 ，则永不过期，默认为 60，即一个小时，单位：分钟 */
    private int tokenExpireTime = 60;

    public AccessToken() {
        String tmp = UUID.randomUUID().toString().replaceAll("-", "");
        this.inheritableThreadLocal.set(tmp);
        this.tokenValue = tmp;
    }

    public AccessToken(String tokenValue) {
        this.inheritableThreadLocal.set(tokenValue);
        this.tokenValue = tokenValue;
    }

    public AccessToken(String tokenValue, int tokenExpireTime) {
        this.tokenValue = tokenValue;
        this.tokenExpireTime = tokenExpireTime;
    }

    @Override
    public String getTokenValue() {
        return this.inheritableThreadLocal.get();
    }

    @Override
    public void setTokenValue(String tokenValue) {
        this.inheritableThreadLocal.set(tokenValue);
    }
}
