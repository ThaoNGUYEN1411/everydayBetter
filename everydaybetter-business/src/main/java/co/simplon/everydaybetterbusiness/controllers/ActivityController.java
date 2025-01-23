package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.dtos.output.ActivityDto;
import co.simplon.everydaybetterbusiness.services.ActivityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.everydaybetterbusiness.dtos.input.ActivityCreate;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping(value = {"","/"})
    @ResponseStatus(HttpStatus.CREATED)
    void createActivity(@Valid @RequestBody final ActivityCreate inputs) {
	    activityService.create(inputs);
    }

    @GetMapping(value = {"","/"})
    @ResponseStatus(HttpStatus.OK)
    List<ActivityDto> getAllActivities(){
        return activityService.getAllActivities();
    }

}

//todo: handle controller advice for unique habit
