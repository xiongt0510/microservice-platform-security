package com.anjuxing.platform.security.validate;


import org.springframework.security.core.AuthenticationException;

/**
 * @author xiongt
 * @Description
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
