package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.common.AppUtils;
import co.simplon.everydaybetterbusiness.dtos.TrackingLogCreate;
import co.simplon.everydaybetterbusiness.dtos.TrackingLogUpdate;
import co.simplon.everydaybetterbusiness.models.ActivitiesProgressAnalyticsModel;
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
@RequestMapping("/tracking-logs")
@SecurityRequirement(name = "bearerAuth")
public class TrackingLogController {

    private final UserActivityTrackingLogService userActivityTrackingLogService;
    private final TrackingLogService trackingLogService;

    public TrackingLogController(UserActivityTrackingLogService userActivityTrackingLogService, TrackingLogService trackingLogService) {
        this.userActivityTrackingLogService = userActivityTrackingLogService;
        this.trackingLogService = trackingLogService;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add tracking of activity by date", description = "Add tracking of activity by date")
    public ResponseEntity<TrackingLogModel> createTrackingLog(@RequestBody @Valid final TrackingLogCreate inputs) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userActivityTrackingLogService.saveTrackingLogForUserActivity(inputs, AppUtils.getAuthenticatedUser()));
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all activities with tracking log", description = "Get all activities with tracking log by date")
    public ResponseEntity<List<ActivityTrackingLogModel>> getAllActivityTrackingLog(@RequestParam(name = "start-date") final LocalDate startDate, @RequestParam(name = "end-date") final LocalDate endDate) {
        return ResponseEntity.status(HttpStatus.OK).body(userActivityTrackingLogService.getTrackingActivityByDay(startDate, endDate, AppUtils.getAuthenticatedUser()));
    }

    @PatchMapping(value = "/update")
    @Operation(summary = "Update a tracking log", description = "update a tracking log according to date and activity")
    public ResponseEntity<Void> updateTrackingActivity(@RequestBody @Valid final TrackingLogUpdate trackingLogUpdate) {
        userActivityTrackingLogService.updateTrackingActivity(trackingLogUpdate, AppUtils.getAuthenticatedUser());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(value = "/")
    @Operation(summary = "Delete a tracking log", description = "Delete a tracking log according to date and activity ")
    public ResponseEntity<Void> deleteTrackingActivity(@RequestParam(name = "id") final Long id) {
        trackingLogService.deleteById(id, AppUtils.getAuthenticatedUser());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "/progress-summary")
    @Operation(summary = "Activities progress analytics", description = "Activities progress analytics by calculator percentage")
    public ResponseEntity<List<ActivitiesProgressAnalyticsModel>> getActivitiesProgressAnalytics(@RequestParam(name = "start-date", required = false) final LocalDate startDate,
            @RequestParam(name = "end-date", required = false) final LocalDate endDate) {
        return ResponseEntity.status(HttpStatus.OK).body(userActivityTrackingLogService.getActivitiesProgressAnalytics(startDate, endDate, AppUtils.getAuthenticatedUser()));
    }
}
