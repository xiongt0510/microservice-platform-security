package com.anjuxing.platform.security.service;

import com.anjuxing.platform.security.model.User;
import org.springframework.stereotype.Service;

/**
 * @author xiongt
 * @Description
 */
@Service
public class UserServiceImpl  {


    public User getUser(String username, String entryType) {

        User user = new User();

        user.setUsername("xiongt");
        user.setPassword("123456");
        user.setMobile("13012345678");

        return user;
    }

}
