package com.anjuxing.platform.security.validate.sms;

/**
 * @author xiongt
 * @Description
 */
public interface SmsCodeSender {

    void  send(String mobile, String code);
}
