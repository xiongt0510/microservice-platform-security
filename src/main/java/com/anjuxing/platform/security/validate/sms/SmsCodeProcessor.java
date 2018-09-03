package com.anjuxing.platform.security.validate.sms;



import com.anjuxing.platform.security.validate.ValidateCode;
import com.anjuxing.platform.security.validate.process.AbstractValidateCodeProcessor;
import com.anjuxing.platform.security.validate.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author xiongt
 * @Description
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor {


    @Autowired
    private SmsCodeSender smsCodeSender;


    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");

        smsCodeSender.send(mobile, validateCode.getCode());
    }
}
