package co.simplon.everydaybetterbusiness.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class JwtHelper {

    public static SecurityContext securityContext() {
        return SecurityContextHolder.getContext();
    }

    public static Authentication authentication() {
        return securityContext().getAuthentication();
    }

    public static Jwt getPrincipal() {
        return (Jwt) authentication().getPrincipal();
    }

    public static String getSubject() {
        return getPrincipal().getClaimAsString("sub");
    }
}
