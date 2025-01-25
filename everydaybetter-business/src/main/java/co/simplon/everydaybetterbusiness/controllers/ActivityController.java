package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.dtos.input.ActivityCreate;
import co.simplon.everydaybetterbusiness.dtos.output.ActivityDto;
import co.simplon.everydaybetterbusiness.models.ActivityModel;
import co.simplon.everydaybetterbusiness.services.ActivityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {
    private final ActivityService service;

    public ActivityController(ActivityService service) {
        this.service = service;
    }

    @PostMapping(value = {"","/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    void createActivity(@Valid @RequestBody final ActivityCreate inputs) {
	    service.create(inputs);
    }

    @GetMapping(value = {"","/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    List<ActivityDto> getAllActivities(){
        return service.getAllActivities();
    }
//@notNull
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ActivityModel getActivityById(@PathVariable Long id){
        return service.findById(id);
    }
}

//todo: handle controller advice for unique habit
