package com.anjuxing.platform.security.config;



import com.anjuxing.platform.security.properties.SecurityProperties;
import com.anjuxing.platform.security.validate.image.ImageCodeGenerator;
import com.anjuxing.platform.security.validate.process.RedisValidateCodeRepository;
import com.anjuxing.platform.security.validate.process.SessionValidateCodeRepository;
import com.anjuxing.platform.security.validate.process.ValidateCodeGenerator;
import com.anjuxing.platform.security.validate.process.ValidateCodeRepository;
import com.anjuxing.platform.security.validate.sms.DefaultSmsCodeSender;
import com.anjuxing.platform.security.validate.sms.SmsCodeGenerator;
import com.anjuxing.platform.security.validate.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiongt
 * @Description
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name ="imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(name = "smsCodeGenerator")
    public ValidateCodeGenerator smsCodeGenerator(){
        SmsCodeGenerator smsCodeGenerator = new SmsCodeGenerator();
        smsCodeGenerator.setSecurityProperties(securityProperties);
        return smsCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){
        return new DefaultSmsCodeSender();
    }

    @Bean
    @ConditionalOnProperty(prefix = "security.validate.code" ,name = "repository" ,havingValue = "session",matchIfMissing = true)
    public ValidateCodeRepository sessionRepository(){
        return new SessionValidateCodeRepository();
    }

    @Bean
    @ConditionalOnProperty(prefix = "security.validate.code" ,name = "repository" ,havingValue = "redis")
    public ValidateCodeRepository redisRepository(){
        return new RedisValidateCodeRepository();
    }
}
