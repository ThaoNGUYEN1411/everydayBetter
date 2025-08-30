package co.simplon.everydaybetterbusiness.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public final class AppUtils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());

    private AppUtils() {
        // Utility class
        throw new IllegalStateException("Utility class");
    }

    public static String getAuthenticatedUser() throws BadCredentialsException {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getClaim("sub");
        }
        throw new BadCredentialsException("User is not authenticated");
    }

    public static ObjectMapper getMapper() {
        return OBJECT_MAPPER;
    }
}
