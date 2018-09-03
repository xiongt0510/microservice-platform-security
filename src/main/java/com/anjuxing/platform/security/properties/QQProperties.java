package com.anjuxing.platform.security.properties;

/**
 * @author xiongt
 * @Description
 */
public class QQProperties {

    /**
     * 第三方id，用来决定发起第三方登录的url，默认是 qq。
     */
    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
