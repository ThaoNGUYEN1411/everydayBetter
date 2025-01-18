package co.simplon.everydaybetterbusiness.config;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class Config {
    @Value("${co.simplon.everydaybetterbusiness.cors}")
    private String origins;

    @Value("${co.simplon.everydaybetterbusiness.cost}")
    private Integer cost;

    @Value("${co.simplon.everydaybetterbusiness.secret}")
    private String secret;

    //need: verify long or Long?
    @Value("${co.simplon.everydaybetterbusiness.jwt.expiration}")
    private long expiration;

    @Value("${co.simplon.everydaybetterbusiness.jwt.issuer}")
    private String issuer;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("POST").allowedOrigins(origins);
            }
        };
    }

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(cost);
    }

    @Bean
    JwtProvider jwtProvider() {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return new JwtProvider(algorithm, expiration, issuer);
    }

    @Bean
    JwtDecoder jwtDecoder() { // Tell Spring how to verify JWT signature
        SecretKey secretKey = new SecretKeySpec(secret.getBytes(), "HMACSHA256");
        NimbusJwtDecoder decoder = NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();

        OAuth2TokenValidator<Jwt> validator = JwtValidators.createDefaultWithIssuer(issuer);
        decoder.setJwtValidator(validator);
        return decoder;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.cors(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req-> req
                        .requestMatchers(HttpMethod.POST, "/users/create", "/users/authenticate").anonymous())

                .authorizeHttpRequests(
                        req -> req.requestMatchers(HttpMethod.GET, "/users/with-role").hasRole("ADMIN"))

                // Always last rule:
                .authorizeHttpRequests(reqs -> reqs.anyRequest().authenticated())
                .oauth2ResourceServer(srv-> srv.jwt(Customizer.withDefaults()))
                .build();

    }
}
