package com.anjuxing.platform.security.config;

import com.anjuxing.platform.security.handler.AuthenticationFailureHandler;
import com.anjuxing.platform.security.handler.AuthenticationSuccessHandler;
import com.anjuxing.platform.security.properties.SecurityProperties;
import com.anjuxing.platform.security.util.SecurityConstants;
import com.anjuxing.platform.security.validate.process.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author xiongt
 * @Description
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Autowired
    private SmsEntryAuthenticationConfig smsEntryAuthenticationConfig;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
//            .apply(smsEntryAuthenticationConfig)
//            .and()
            .formLogin()
            .loginPage(SecurityConstants.AUTHORIZATION_REQUIRE)
            .and()
            .authorizeRequests()
            .antMatchers(SecurityConstants.AUTHORIZATION_REQUIRE,
                    SecurityConstants.VALIDATE_CODE_URL,
                    securityProperties.getForm().getPage(),
                    SecurityConstants.AUTHORIZATION_USERNAME,
                    SecurityConstants.AUTHORIZATION_MOBILE).permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .csrf()
            .disable();
    }
}
