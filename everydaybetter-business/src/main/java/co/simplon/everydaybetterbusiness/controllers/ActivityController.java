package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.dtos.ActivityCreate;
import co.simplon.everydaybetterbusiness.services.ActivityManagerService;
import co.simplon.everydaybetterbusiness.services.ActivityService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = {"","/"})
    ResponseEntity<Void> createActivity(@Valid @RequestBody final ActivityCreate inputs) {
        activityManagerService.create(inputs);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    @GetMapping(value = {"","/"}, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    List<ActivityDto> getAllActivitiesByUser(){
//        return service.getAllActivitiesByUser();
//    }
//
//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    ActivityModel getActivityById(@PathVariable Long id){
//        return service.findById(id);
//    }
//
//    @DeleteMapping("/{id}")
//    void delete(@PathVariable("id") Long id) {
//        service.delete(id);
//    }
//
//    @PutMapping("/{id}")
//    void update(@PathVariable Long id, @ModelAttribute ActivityUpdate inputs) {
//        service.update(id, inputs);
//    }

    //todo: handle controller advice for unique habit
//Validation inputs
//learn more @ModelAttribute

}

//todo: handle controller advice for unique habit
//Validation inputs
//learn more @ModelAttribute
