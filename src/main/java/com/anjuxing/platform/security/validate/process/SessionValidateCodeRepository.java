package com.anjuxing.platform.security.validate.process;


import com.anjuxing.platform.security.validate.ValidateCode;
import com.anjuxing.platform.security.validate.ValidateCodeType;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author xiongt
 * @Description
 */

public class SessionValidateCodeRepository implements ValidateCodeRepository {


    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();


    @Override
    public void save(ServletWebRequest request, ValidateCode validateCode, ValidateCodeType type) {
        sessionStrategy.setAttribute(request,getSessionKey(type),validateCode);
    }

    @Override
    public void remove(ServletWebRequest request,  ValidateCodeType type) {
        sessionStrategy.removeAttribute(request,getSessionKey(type));
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
        return (ValidateCode) sessionStrategy.getAttribute(request,getSessionKey(type));
    }

    public String getSessionKey(ValidateCodeType type){
        return  SESSION_KEY_PREFIX + type.toString().toUpperCase();
    }
}
