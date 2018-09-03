package com.anjuxing.platform.security.controller;

import com.anjuxing.platform.security.handler.SecurityResponse;
import com.anjuxing.platform.security.model.EntryType;
import com.anjuxing.platform.security.model.User;
import com.anjuxing.platform.security.properties.SecurityProperties;
import com.anjuxing.platform.security.service.UserService;
import com.anjuxing.platform.security.util.SecurityConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author xiongt
 * @Description
 */
@RestController
public class AuthorizationController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /** request 缓存对象，会所请求的信息封装到session中 */
    private RequestCache requestCache = new HttpSessionRequestCache();
    /** 重定向策略 */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @PostMapping(SecurityConstants.AUTHORIZATION_USERNAME)
    public User  getUserByUsername(@RequestParam("username") String username ,
                                   @RequestParam("password") String password,
                                   HttpServletResponse response){

        logger.info("response :" + response);

        User user  = userService.loadUserByUsername(username,password,
                EntryType.USERNAME,getAuthorization());

        return user;

    }

    @PostMapping(SecurityConstants.AUTHORIZATION_MOBILE)
    public User  getUserByUsername(@RequestParam("mobile") String mobile){
        User user  = userService.loadUserByMobile(mobile, EntryType.MOBILE,getAuthorization());
        return user;
    }


    private String getAuthorization(){
        String clientId = securityProperties.getClient().getId();
        String clientSecret = securityProperties.getClient().getSecret();

        String authorization = com.anjuxing.platform.security.util.StringUtils.
                getBasicAuthorization(clientId,clientSecret);

        return authorization;
    }







    /**
     * 请求跳转
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(SecurityConstants.AUTHORIZATION_REQUIRE)
    @ResponseStatus(code =  HttpStatus.UNAUTHORIZED)
    public SecurityResponse<String> requireAuthentication(HttpServletRequest request , HttpServletResponse response) throws Exception {

        //从requestCache 中获取 request 信息
        SavedRequest savedRequest = requestCache.getRequest(request,response);

        if (Objects.nonNull(savedRequest)){
            //重新拿到请求的url
            String targetUrl = savedRequest.getRedirectUrl();

            logger.info("request url is " + targetUrl);

            if (matchEndsWith(targetUrl)){
                redirectStrategy.sendRedirect(request,response,securityProperties.getForm().getPage());
            }
        }

        return new SecurityResponse<String>("访问的服务需要身份认证，请引导用户到登录页");
    }

    /**
     * 匹配后缀
     * @param targetUrl
     * @return
     */
    private boolean matchEndsWith(String targetUrl ){

        boolean isMatch = false;

        //获取配置的后缀字符串
        String suffixes = securityProperties.getForm().getUrlSuffixes();

        String [] suffixArray = StringUtils.splitByWholeSeparator(suffixes,SecurityConstants.COMMA);

        for (String suffix : suffixArray){

            if (StringUtils.endsWithIgnoreCase(targetUrl,SecurityConstants.POINT+suffix)){
                isMatch = true;
                break;
            }
        }

        return isMatch;


    }


}
