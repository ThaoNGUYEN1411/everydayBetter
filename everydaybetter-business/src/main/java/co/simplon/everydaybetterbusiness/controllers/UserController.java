package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.dtos.UserAuthenticate;
import co.simplon.everydaybetterbusiness.dtos.UserCreate;
import co.simplon.everydaybetterbusiness.dtos.output.AuthInfo;
import co.simplon.everydaybetterbusiness.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    void create( @RequestBody UserCreate inputs){
        service.create(inputs);
    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.CREATED)
    AuthInfo authenticate(@Valid @RequestBody UserAuthenticate inputs, HttpServletResponse response) {
        return service.authenticate(inputs, response);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", "");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // Expire immédiatement
        response.addCookie(cookie);

        return ResponseEntity.ok().body(Map.of("message", "Logged out successfully"));
    }
}

//Note: in the project entreprise, controller have "public" methode