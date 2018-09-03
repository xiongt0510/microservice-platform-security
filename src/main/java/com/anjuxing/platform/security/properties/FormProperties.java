package com.anjuxing.platform.security.properties;

import com.anjuxing.platform.security.handler.ResponseMode;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xiongt
 * @Description  用户进入系统的相关配置
 */
public class FormProperties {

    /** 进入系统默认跳转页面 */
    private String page = "/login.html";

    /** form 登录后处理的相关url */
    private String processUrl = "/authorization/form";

    /** form 登录后的处理响应方式  */
    private ResponseMode responseMode = ResponseMode.JSON;

    /** 浏览器访问地址的后缀 */
    private String urlSuffixes = "html";



    public String getProcessUrl() {
        return processUrl;
    }

    public void setProcessUrl(String processUrl) {
        this.processUrl = processUrl;
    }

    public ResponseMode getResponseMode() {
        return responseMode;
    }

    public void setResponseMode(ResponseMode responseMode) {
        this.responseMode = responseMode;
    }

    public String getUrlSuffixes() {
        return urlSuffixes;
    }

    public void setUrlSuffixes(String urlSuffixes) {
        this.urlSuffixes = urlSuffixes;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
