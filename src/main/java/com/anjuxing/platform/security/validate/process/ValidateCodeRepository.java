package com.anjuxing.platform.security.validate.process;


import com.anjuxing.platform.security.validate.ValidateCode;
import com.anjuxing.platform.security.validate.ValidateCodeType;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author xiongt
 * @Description
 * 验证码存储器接口
 */
public interface ValidateCodeRepository {

    void  save(ServletWebRequest request, ValidateCode validateCode, ValidateCodeType type);

    void remove(ServletWebRequest request, ValidateCodeType type);

    ValidateCode get(ServletWebRequest request, ValidateCodeType type);
}
