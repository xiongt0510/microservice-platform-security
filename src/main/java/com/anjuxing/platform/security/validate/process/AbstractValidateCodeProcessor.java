package com.anjuxing.platform.security.validate.process;


import com.anjuxing.platform.security.validate.ValidateCode;
import com.anjuxing.platform.security.validate.ValidateCodeException;
import com.anjuxing.platform.security.validate.ValidateCodeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @author xiongt
 * @Description
 */
public abstract class AbstractValidateCodeProcessor<V extends ValidateCode>  implements ValidateCodeProcessor  {


    @Autowired
    private Map<String,ValidateCodeGenerator> validateCodeGenerators ;

    @Autowired
    private ValidateCodeRepository validateCodeRepository;


    @Override
    public void create(ServletWebRequest request) throws Exception {
        V validateCode = generate(request);
        save(request,validateCode);
        send(request,validateCode);

    }

    /**
     * 获取生产code 的子类
     * @param request
     * @return
     */
    protected  V generate(ServletWebRequest request){

        //获取类型名
        String type = getValidateCodeType(request).toString().toLowerCase();

        //得到产生code 的map 名
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();

        ValidateCodeGenerator codeGenerator = validateCodeGenerators.get(generatorName);

        if (codeGenerator == null) {
            throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
        }

        return (V)codeGenerator.generate(request);



    }

    /**
     * 保存校验码
     *
     * @param request
     * @param validateCode
     */
    private void save(ServletWebRequest request,ValidateCode  validateCode) {
        ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        validateCodeRepository.save(request, code, getValidateCodeType(request));
    }

    /**
     * 发送校验码，由子类实现
     *
     * @param request
     * @param validateCode
     * @throws Exception
     */
    protected abstract void send(ServletWebRequest request, V validateCode) throws Exception;

    /**
     * 从当前实际的类中获取类型
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(ServletWebRequest request){
        String type = StringUtils.substringBefore(getClass().getSimpleName(),"CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    @Override
    public void validate(ServletWebRequest request) {

        ValidateCodeType codeType = getValidateCodeType(request);

        V codeInSession = (V) validateCodeRepository.get(request,codeType);


        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    codeType.getParamName());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(codeType + "验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException(codeType + "验证码不存在");
        }

        if (codeInSession.isExpried()) {
            validateCodeRepository.remove(request, codeType);
            throw new ValidateCodeException(codeType + "验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException(codeType + "验证码不匹配");
        }

        validateCodeRepository.remove(request, codeType);
    }


}
