package co.simplon.everydaybetterbusiness.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;

public class CookieTokenResolver implements BearerTokenResolver {

    private static final String COOKIE_NAME = "jwt"; // nom de ton cookie

    @Override
    public String resolve(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (COOKIE_NAME.equals(cookie.getName())) {
                    return cookie.getValue(); // retourne le token depuis le cookie
                }
            }
        }
        return null;
    }
}
/*
Par défaut, Spring Security lit le token JWT dans l’en-tête Authorization: Bearer ..., pas dans les cookies.
Donc même si ton front envoie bien le cookie (withCredentials: true), le back ne lit rien, et ça finit en erreur 401 Unauthorized.
Tu veux que Spring récupère le token JWT depuis un cookie (par exemple un cookie nommé jwt-token
Crée un BearerTokenResolver personnalisé
 */