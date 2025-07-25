package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.common.AppUtils;
import co.simplon.everydaybetterbusiness.dtos.ActivityCreate;
import co.simplon.everydaybetterbusiness.dtos.ActivityUpdate;
import co.simplon.everydaybetterbusiness.models.ActivityDetailModel;
import co.simplon.everydaybetterbusiness.models.ActivityModel;
import co.simplon.everydaybetterbusiness.services.ActivityManagerService;
import co.simplon.everydaybetterbusiness.services.UserActivityTrackingLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activities")
@SecurityRequirement(name = "bearerAuth")
public class ActivityController {

    private final ActivityManagerService activityManagerService;
    private final UserActivityTrackingLogService userActivityTrackingLogService;

    public ActivityController(ActivityManagerService activityManagerService, UserActivityTrackingLogService userActivityTrackingLogService) {
        this.activityManagerService = activityManagerService;
        this.userActivityTrackingLogService = userActivityTrackingLogService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create an activity", description = "Create an activity for the currently connected user")
    public ResponseEntity<ActivityModel> createActivity(@Valid @RequestBody final ActivityCreate inputs) {
        return ResponseEntity.status(HttpStatus.CREATED).body(activityManagerService.create(inputs, AppUtils.getAuthenticatedUser()));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all activities for an user", description = "Get all activities for an user")
    public ResponseEntity<List<ActivityModel>> getAllActivitiesByUser() {
        return ResponseEntity.status(HttpStatus.OK).body(activityManagerService.getAllActivitiesByUser(AppUtils.getAuthenticatedUser()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get an activity by id", description = "Get an activity by id")
    public ResponseEntity<ActivityDetailModel> getActivityById(@PathVariable final Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(activityManagerService.findById(id, AppUtils.getAuthenticatedUser()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an activity by id", description = "Delete an activity by id")
    public ResponseEntity<Void> update(@PathVariable("id") final Long id, @RequestBody @Valid final ActivityUpdate inputs) {
        activityManagerService.update(id, inputs, AppUtils.getAuthenticatedUser());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an activity by id", description = "Delete an activity by id")
    public ResponseEntity<Void> delete(@PathVariable("id") final Long id) {
        userActivityTrackingLogService.deleteActivityById(id, AppUtils.getAuthenticatedUser());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
