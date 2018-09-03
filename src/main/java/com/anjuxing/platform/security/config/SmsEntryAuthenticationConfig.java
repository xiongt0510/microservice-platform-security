package com.anjuxing.platform.security.config;


import com.anjuxing.platform.security.properties.SecurityProperties;
import com.anjuxing.platform.security.service.UserService;
import com.anjuxing.platform.security.validate.sms.entry.MobileAuthenticationFilter;
import com.anjuxing.platform.security.validate.sms.entry.MobileAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author xiongt
 * @Description
 */
@Component
public class SmsEntryAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityProperties securityProperties;








    @Override
    public void configure(HttpSecurity http) throws Exception {
        MobileAuthenticationFilter authenticationFilter = new MobileAuthenticationFilter();
        authenticationFilter.setAuthenticationFailureHandler(failureHandler);
        authenticationFilter.setAuthenticationSuccessHandler(successHandler);
        authenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));

        MobileAuthenticationProvider authenticationProvider = new MobileAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setUserService(userService);
        authenticationProvider.setSecurityProperties(securityProperties);

        http.authenticationProvider(authenticationProvider)
                .addFilterAfter(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
