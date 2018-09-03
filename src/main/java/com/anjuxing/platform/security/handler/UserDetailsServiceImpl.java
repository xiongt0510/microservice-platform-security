package com.anjuxing.platform.security.handler;

import com.anjuxing.platform.security.model.User;
import com.anjuxing.platform.security.properties.SecurityProperties;
import com.anjuxing.platform.security.service.UserService;
import com.anjuxing.platform.security.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author xiongt
 * @Description
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String clientId = securityProperties.getClient().getId();
        String clientSecret = securityProperties.getClient().getSecret();

        String authorization = StringUtils.getBasicAuthorization(clientId,clientSecret);

        User user = userService.loadUserByUsername(username,null,null,authorization);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return new UserDetailsImpl(user);
    }
}
