package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.dtos.input.UserAuthenticate;
import co.simplon.everydaybetterbusiness.dtos.input.UserCreate;
import co.simplon.everydaybetterbusiness.dtos.output.AuthInfo;
import co.simplon.everydaybetterbusiness.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    void create(@Valid @RequestBody UserCreate inputs){
        service.create(inputs);
    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.CREATED)
    AuthInfo authentificate(@Valid @RequestBody UserAuthenticate inputs) {
        return service.authenticate(inputs);
    }
}

//Note: in the project entreprise, controller have "public" methode