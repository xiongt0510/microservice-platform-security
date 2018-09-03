package com.anjuxing.platform.security.validate.sms.entry;

import com.anjuxing.platform.security.handler.UserDetailsImpl;
import com.anjuxing.platform.security.model.User;
import com.anjuxing.platform.security.properties.SecurityProperties;
import com.anjuxing.platform.security.service.UserService;

import com.anjuxing.platform.security.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * @author xiongt
 * @Description
 */
public class MobileAuthenticationProvider implements AuthenticationProvider {

    static Logger logger = LoggerFactory.getLogger(MobileAuthenticationProvider.class);



    private UserService userService;

    private SecurityProperties securityProperties;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        //得到封装的信息
        MobileAuthenticationToken mobileAuthenticationToken = (MobileAuthenticationToken)authentication;

        String clientId = securityProperties.getClient().getId();

        String clientSecret = securityProperties.getClient().getSecret();

       String authorization = StringUtils.getBasicAuthorization(clientId,clientSecret);

       User user =  userService.loadUserByMobile((String) mobileAuthenticationToken.getPrincipal(),null,authorization);

        UserDetails userDetails = new UserDetailsImpl(user);

//        UserDetails user = userDetailsService.loadUserByUsername((String) mobileAuthenticationToken.getPrincipal());

        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        //把从数据库得到信息的再次封装到 token中
        MobileAuthenticationToken authenticationResult = new MobileAuthenticationToken(userDetails,userDetails.getAuthorities());

        //把请求信息设置 进去
        authenticationResult.setDetails(mobileAuthenticationToken.getDetails());
        return authenticationResult;
    }

    /**
     * 支持当前provider
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return MobileAuthenticationToken.class.isAssignableFrom(authentication);
    }


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
