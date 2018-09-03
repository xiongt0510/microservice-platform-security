package com.anjuxing.platform.security.controller;



import com.anjuxing.platform.security.validate.process.ValidateCodeProcessor;
import com.anjuxing.platform.security.validate.process.ValidateCodeProcessorHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiongt
 * @Description
 *  验证码控制器
 */
@RestController
public class ValidateCodeController {


    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;


    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response ,
    @PathVariable String type) throws Exception {

        ValidateCodeProcessor validateCodeProcessor = validateCodeProcessorHolder.findValidateCodeProcessor(type);

        validateCodeProcessor.create(new ServletWebRequest(request,response));
    }
}
