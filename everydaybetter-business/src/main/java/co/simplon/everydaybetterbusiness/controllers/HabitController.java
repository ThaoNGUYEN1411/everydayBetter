package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.services.HabitService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.everydaybetterbusiness.dtos.input.HabitCreate;

@RestController
@RequestMapping("/habits")
public class HabitController {
    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @PostMapping(value = {"","/"})
    @ResponseStatus(HttpStatus.CREATED)
    void createHabit(@Valid @RequestBody final HabitCreate inputs) {
	    habitService.create(inputs);
    }
}
