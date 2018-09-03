package com.anjuxing.platform.security.validate.process;

import com.anjuxing.platform.security.validate.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author xiongt
 * @Description
 *
 * 验证码生成器
 *
 */
public interface ValidateCodeGenerator {

    /**
     * 生成验证码
     * @return
     */
    ValidateCode generate(ServletWebRequest request);

}
