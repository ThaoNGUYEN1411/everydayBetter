package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.dtos.UserAuthenticate;
import co.simplon.everydaybetterbusiness.dtos.UserCreate;
import co.simplon.everydaybetterbusiness.models.AuthInfo;
import co.simplon.everydaybetterbusiness.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> create(@Valid @RequestBody UserCreate inputs){
        service.create(inputs);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AuthInfo> authenticate(@Valid @RequestBody UserAuthenticate inputs, HttpServletResponse response) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.authenticate(inputs, response));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", "");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // Expire immédiatement
        response.addCookie(cookie);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Logged out successfully"));
        //todo Thao: to handle remove cookie front
    }
}

//Note: in the project entreprise, controller have "public" methode