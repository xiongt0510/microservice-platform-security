package com.anjuxing.platform.security.validate.sms;

import com.anjuxing.platform.security.validate.ValidateCode;

import java.time.LocalDateTime;

/**
 * @author xiongtao
 * @Description 短信验证码
 * @date 2018-08-24 下午 8:45
 */
public class SmsCode extends ValidateCode {

    public SmsCode(String code, int expireIn) {
        super(code, expireIn);
    }

    public SmsCode(String code, LocalDateTime expireTime) {
        super(code, expireTime);
    }
}
