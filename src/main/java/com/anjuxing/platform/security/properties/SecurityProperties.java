package com.anjuxing.platform.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xiongt
 * @Description
 */
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    /** 系统登录的相关配置 */
    private FormProperties form = new FormProperties();
    /**
     * 验证码配置
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();

    /** 访问认证服务器客户端配置 */
    private OAuth2ClientPropertes client = new OAuth2ClientPropertes();


    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public FormProperties getForm() {
        return form;
    }

    public void setForm(FormProperties form) {
        this.form = form;
    }

    public OAuth2ClientPropertes getClient() {
        return client;
    }

    public void setClient(OAuth2ClientPropertes client) {
        this.client = client;
    }
}
