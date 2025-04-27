package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.dtos.ActivityCreate;
import co.simplon.everydaybetterbusiness.models.ActivityDetailModel;
import co.simplon.everydaybetterbusiness.models.ActivityModel;
import co.simplon.everydaybetterbusiness.services.ActivityManagerService;
import co.simplon.everydaybetterbusiness.services.ActivityService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activities")
@SecurityRequirement(name = "bearerAuth")
public class ActivityController {
    private final ActivityService service;
    private final ActivityManagerService activityManagerService;

    public ActivityController(ActivityService service, ActivityManagerService activityManagerService) {
        this.service = service;
        this.activityManagerService = activityManagerService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> createActivity(@Valid @RequestBody final ActivityCreate inputs) {
        activityManagerService.create(inputs);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ActivityModel>> getAllActivitiesByUser(){
        return ResponseEntity.status(HttpStatus.OK).body(activityManagerService.getAllActivitiesByUser());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ActivityDetailModel> getActivityById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(activityManagerService.findById(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

//    @PutMapping("/{id}")
//    ResponseEntity<Void> update(@PathVariable Long id, @ModelAttribute ActivityUpdate inputs) {
//        service.update(id, inputs);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

}

//todo: handle controller advice for unique habit
//Validation inputs
//learn more @ModelAttribute
