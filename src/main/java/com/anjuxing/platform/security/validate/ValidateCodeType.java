package com.anjuxing.platform.security.validate;



/**
 * @author xiongt
 * @Description 校验码类型
 */
public enum ValidateCodeType {

    /**
     * 图片验证码
     */
    IMAGE {
        @Override
        public String getParamName() {
            return ValidateCodeConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    } ,

    /**
     * 短信验证码
     */
    SMS {
        @Override
        public String getParamName() {
            return ValidateCodeConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    };

    /**
     * 校验时从请求中获取的参数的名字
     * @return
     */
    public abstract String getParamName();
}
