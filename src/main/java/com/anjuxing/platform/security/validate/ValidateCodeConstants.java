package com.anjuxing.platform.security.validate;

/**
 * @author xiongt
 * @Description
 */
public interface ValidateCodeConstants {

    String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";

    String SESSION_KEY_SMS_CODE = "SESSION_KEY_SMS_CODE";

    String VALIDATE_CODE_URL = "/code/*";

    /**
     * 默认的用户名密码登录请求处理url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_FORM = "/authorization/form";
    /**
     * 默认的手机验证码登录请求处理url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE = "/authorization/mobile";

    String DEFAULT_PARAMETER_NAME_CODE_IMAGE ="imageCode";

    String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";


}
