package com.anjuxing.platform.security.validate.process;

import com.anjuxing.platform.security.validate.ValidateCodeException;
import com.anjuxing.platform.security.validate.ValidateCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xiongt
 * @Description
 */
@Component
public class ValidateCodeProcessorHolder {

    @Autowired
    private Map<String ,ValidateCodeProcessor> validateCodeProcessors;


    /**
     * @param type
     * @return
     */
    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

    /**
     * 根据类型找到一个验证码处理器
     * @param type
     * @return
     */
    public  ValidateCodeProcessor findValidateCodeProcessor(String type){

        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();

        ValidateCodeProcessor processor = validateCodeProcessors.get(name);

        if (processor == null) {
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return processor;

    }


}
