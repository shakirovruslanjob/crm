package com.ruslanshakirov.crm.util;


import com.ruslanshakirov.crm.AuthorizedUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuthUtil {
    public static String getCurrentUserEmail() {
        return getCurrentUser().getUsername();
    }

    public static AuthorizedUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Optional.ofNullable(authentication)
                .map(Authentication::getPrincipal)
                .filter(principal -> principal instanceof AuthorizedUser)
                .map(principal -> (AuthorizedUser) principal)
                .orElseThrow(() -> new RuntimeException("No authorized user found"));
    }
}
