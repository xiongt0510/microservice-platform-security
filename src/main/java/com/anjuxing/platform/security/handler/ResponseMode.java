package com.anjuxing.platform.security.handler;

/**
 * @author xiongtao
 * @Description 响应方式，是以json 响应还是以 重定向的方式响应
 */
public enum ResponseMode {

    REDIRECT ,   // 登录成功可失败后 重定向到其他页面
    JSON         //登录成功或失败后，用json格式展示

}
