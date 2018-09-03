package com.anjuxing.platform.security.validate.process;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author xiongt
 * @Description
 * 验证码处理器接口
 */
public interface ValidateCodeProcessor {

    /**
     * 创建验证码
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     *
     * @param servletWebRequest
     * @throws Exception
     */
    void validate(ServletWebRequest servletWebRequest);
}
