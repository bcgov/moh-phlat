package com.moh.phlat.backend.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.util.StringUtils;

public class AuthenticationUtils {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationUtils.class);


    public static String getAuthenticatedUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return parseUserName(authentication.getName());

    }

    private static String parseUserName(String name) {
        if (StringUtils.hasText(name)) {
            return name;
        } else {
            logger.warn("The logged-in user does not have a name. Please investigate whether " +
                                "the preferred user name attribute is being returned or not from IDP");
            //try to retrieve email instead
            return getUserEmail();
        }

    }

    private static String getUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = "";
        if (authentication instanceof JwtAuthenticationToken jwt) {
            email = jwt.getToken().getClaimAsString("email");
            if (!StringUtils.hasText(email)) {
                email = "";
            }
        }
        return email;
    }


}
