package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.common.AppUtils;
import co.simplon.everydaybetterbusiness.dtos.TrackingLogCreate;
import co.simplon.everydaybetterbusiness.dtos.TrackingLogUpdate;
import co.simplon.everydaybetterbusiness.models.ActivityTrackingLogModel;
import co.simplon.everydaybetterbusiness.models.TrackingLogModel;
import co.simplon.everydaybetterbusiness.services.TrackingLogService;
import co.simplon.everydaybetterbusiness.services.UserActivityTrackingLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tracking-log")
@SecurityRequirement(name = "bearerAuth")
public class TrackingLogController {
    private final UserActivityTrackingLogService userActivityTrackingLogService;
    private final TrackingLogService trackingLogService;

    public TrackingLogController(UserActivityTrackingLogService userActivityTrackingLogService, TrackingLogService trackingLogService) {
        this.userActivityTrackingLogService = userActivityTrackingLogService;
        this.trackingLogService = trackingLogService;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrackingLogModel> saveUserActivityLog(@RequestBody @Valid final TrackingLogCreate inputs){
        return ResponseEntity.status(HttpStatus.CREATED).body(userActivityTrackingLogService.saveTrackingLogForUserActivity(inputs, AppUtils.getAuthenticatedUser()));
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all activities with tracking log", description = "Get all activities with tracking log by date")
    public ResponseEntity<List<ActivityTrackingLogModel>> getTrackingActivityByDay(@RequestParam(name = "start-date") LocalDate startDate, @RequestParam(name = "end-date") LocalDate endDate){
        return ResponseEntity.status(HttpStatus.OK).body(userActivityTrackingLogService.getTrackingActivityByDay(startDate, endDate, AppUtils.getAuthenticatedUser()));
    }

    @PatchMapping(value = "/update")
    @Operation(summary = "Update a tracking log", description = "update a tracking log according to date and activity ")
    public ResponseEntity<Void> updateTrackingActivity(@RequestBody @Valid final TrackingLogUpdate trackingLogUpdate){
        trackingLogService.updateTrackingActivity(trackingLogUpdate);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(value = "/")
    public ResponseEntity<Void> deleteTrackingActivity(@RequestParam(name = "id") Long id){
        trackingLogService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
//todo: add operation to all controller
