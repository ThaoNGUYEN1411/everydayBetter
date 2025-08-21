package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.dtos.UserAuthenticate;
import co.simplon.everydaybetterbusiness.dtos.UserCreate;
import co.simplon.everydaybetterbusiness.models.AuthInfo;
import co.simplon.everydaybetterbusiness.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Create a new account",
            description = "Create a new account"
    )
    public ResponseEntity<Void> create(
            @Valid @RequestBody final UserCreate inputs
    ) {
        service.create(inputs);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(
            value = "/authenticate",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "User authenticate", description = "User authenticate")
    public ResponseEntity<AuthInfo> authenticate(
            @Valid @RequestBody final UserAuthenticate inputs,
            HttpServletResponse response
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.authenticate(inputs, response));
    }

    @PostMapping(value = "/logout")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "User logout", description = "User logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        service.logout(response);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
