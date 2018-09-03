package com.anjuxing.platform.security.validate.sms.entry;


import com.anjuxing.platform.security.validate.ValidateCodeConstants;
import com.anjuxing.platform.security.validate.ValidateCodeException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiongt
 * @Description
 */

public class MobileAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    /** 请求的mobile字段 */
    public static final String SPRING_SECURITY_FORM_MOBILE_KEY = "mobile";

    private String mobileParamter = SPRING_SECURITY_FORM_MOBILE_KEY;

    //是否仅仅支持post 请求
    private boolean postOnly = true;


    /**
     * 当前仅仅是 mobile认证请求，post 方法时，才使用此过滤器
     */
    public MobileAuthenticationFilter() {
        super(new AntPathRequestMatcher(ValidateCodeConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE, "POST"));
    }

    /**
     * 获取mobile 参数的值
     * @param request
     * @return
     */
    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(mobileParamter);
    }

    /**
     * 把request 请求信息设置 到 MobileAuthenticationToken中进行封装
     * @param request
     * @param authRequest
     */
    protected void setDetails(HttpServletRequest request,
                              MobileAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public String getMobileParamter() {
        return mobileParamter;
    }

    public void setMobileParamter(String mobileParamter) {
        Assert.hasText(mobileParamter, "Mobile parameter must not be empty or null");
        this.mobileParamter = mobileParamter;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        //如果不是一个post的请求
        if (postOnly && !request.getMethod().equals("POST")){
            throw  new ValidateCodeException("Authentication method not supported:"+request.getMethod());
        }

        String mobile = obtainMobile(request);

        if (StringUtils.isEmpty(mobile)){
            mobile = "";
        }
        mobile = mobile.trim();

        MobileAuthenticationToken entryAuthenticationToken = new MobileAuthenticationToken(mobile);

        // Allow subclasses to set the "details" property
        setDetails(request,entryAuthenticationToken);

        return this.getAuthenticationManager().authenticate(entryAuthenticationToken);
    }
}
