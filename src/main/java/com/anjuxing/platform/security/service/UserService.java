package com.anjuxing.platform.security.service;

import com.anjuxing.platform.security.model.EntryType;
import com.anjuxing.platform.security.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import  static com.anjuxing.platform.security.util.FeignConstants.*;

/**
 * @author xiongt
 * @Description  用户接口这个接口调用远程服务
 */
@FeignClient(name= MICROSERVER_PLATFORM_SECURITY_OAUTH2 )
public interface UserService {

    @RequestMapping(value = "/user/username" ,method = RequestMethod.POST )
    User loadUserByUsername(@RequestParam("username")  String username,
                    @RequestParam("password") String password,
                    @RequestParam("entryType") EntryType entryType,
                 @RequestHeader("Authorization") String authorization);


    @RequestMapping(value = URL_USER_MOBILE ,method = RequestMethod.POST)
    User loadUserByMobile(@RequestParam("mobile") String mobile ,
                          @RequestParam("entryType") EntryType entryType,
                          @RequestHeader("Authorization") String authorization);




}
