package co.simplon.everydaybetterbusiness.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;

@Configuration
public class EverydayBetterConfig implements BearerTokenResolver {

    private static final String COOKIE_NAME = "jwt";
    private final BusinessConfig businessConfig;
    private final JwtConfig jwtConfig;


    public EverydayBetterConfig(final BusinessConfig businessConfig, final JwtConfig jwtConfig) {
        this.businessConfig = businessConfig;
        this.jwtConfig = jwtConfig;
    }

    public BusinessConfig getBusinessConfig() {
        return businessConfig;
    }

    public JwtConfig getJwtConfig() {
        return jwtConfig;
    }

    @Override
    public String resolve(final HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (COOKIE_NAME.equals(cookie.getName())) {
                    return cookie.getValue(); // retourne le token depuis le cookie
                }
            }
        }
        return null;
    }

    @ConfigurationProperties(prefix = "co.simplon.everydaybetterbusiness")
    public record BusinessConfig(String origins, @Min(value = 4) Integer cost, String secret) {
    }

    @ConfigurationProperties(prefix = "co.simplon.everydaybetterbusiness.jwt")
    public record JwtConfig(Long expiration, String issuer) {
    }
}
