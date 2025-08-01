package co.simplon.everydaybetterbusiness.common;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class AppUtils {

    public static String getAuthenticatedUser() throws BadCredentialsException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getClaim("sub");
        }
        throw new BadCredentialsException("User is not authenticated");
    }
}
