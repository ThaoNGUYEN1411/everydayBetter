package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.services.HabitService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.everydaybetterbusiness.dtos.input.HabitCreate;

@RestController
@RequestMapping("/habits")
@AllArgsConstructor
public class HabitController {
    private final HabitService habitService;

    @PostMapping(value = {"","/"})
    public ResponseEntity<Void> createHabit(@Valid @RequestBody final HabitCreate inputs) {
	habitService.create(inputs);

    return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
