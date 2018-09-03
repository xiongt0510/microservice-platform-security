package com.anjuxing.platform.security.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.crypto.codec.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

/**
 * @author xiongt
 * @Description
 */
public class StringUtils {

    private StringUtils(){}

   static   Logger logger = LoggerFactory.getLogger(StringUtils.class);

    public static String getBasicAuthorization(String clientId ,String clientSecret)  {
        String joinClient = org.apache.commons.lang3.StringUtils.joinWith(":",clientId,clientSecret);

        logger.info("clientId and clientSecret is :" + joinClient);

        if (Objects.isNull(joinClient)){
            logger.debug("clientId and clientSecret is null ,can't get token.");
            throw new NullPointerException("没有配置客户端ID和密码不能获得token");
        }

        String authorization = "Basic ";
        try {
            byte [] codes   = Base64.encode(joinClient.getBytes("UTF-8"));
            authorization += new String(codes,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new InternalAuthenticationServiceException("加密客户端id和secret 失败");
        }

        return authorization;
    }









}
