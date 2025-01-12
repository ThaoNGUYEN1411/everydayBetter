package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.dtos.input.AccountCreate;
import co.simplon.everydaybetterbusiness.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody AccountCreate inputs){
        service.create(inputs);

    }
}

//Note: in the project entreprise, controller have "public" methode