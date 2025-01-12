package co.simplon.everydaybetterbusiness.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config {
    @Value("${co.simplon.everydaybetterbusiness.cors}")
    private String origins;

    @Value("${co.simplon.everydaybetterbusiness.cost}")
    private Integer cost;

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
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.cors(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req-> req
                        .requestMatchers(HttpMethod.POST, "/accounts/create").anonymous())
                .build();

    }
}
