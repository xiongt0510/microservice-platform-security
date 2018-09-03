package com.anjuxing.platform.security.handler;

import com.anjuxing.platform.security.properties.SecurityProperties;
import com.anjuxing.platform.security.util.SecurityConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiongt
 * @Description
 */
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        logger.info("ResponseMode is :" + securityProperties.getForm().getResponseMode());
        logger.info("Authentication is :"+ authentication);
        if (ResponseMode.JSON.equals(securityProperties.getForm().getResponseMode())){
            response.setContentType(SecurityConstants.CONTENT_TYPE_APPLICATION_JSON);
            response.getWriter().write(objectMapper.writeValueAsString(authentication.getPrincipal()));
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }


    }
}
