package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.common.AppUtils;
import co.simplon.everydaybetterbusiness.dtos.TrackingRecordCreate;
import co.simplon.everydaybetterbusiness.dtos.TrackingRecordUpdate;
import co.simplon.everydaybetterbusiness.models.ActivityTrackingRecordModel;
import co.simplon.everydaybetterbusiness.models.TrackingRecordModel;
import co.simplon.everydaybetterbusiness.services.UserActivityTrackingRecordService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/tracking-record")
@SecurityRequirement(name = "bearerAuth")
public class TrackingRecordController {
    private final UserActivityTrackingRecordService service;

    public TrackingRecordController(UserActivityTrackingRecordService service) {
        this.service = service;
    }

    //choix between return void or an object TrackingRecordDto => update direct state, it's better to return an object
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrackingRecordModel> saveUserActivityRecord(@RequestBody @Valid final TrackingRecordCreate inputs){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveTrackingRecordForUserActivity(inputs, AppUtils.getAuthenticatedUser()));
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ActivityTrackingRecordModel>> getTrackingActivityByDay(@RequestParam(name = "start-date")LocalDate startDate, @RequestParam(name = "end-date") LocalDate endDate){
        return ResponseEntity.status(HttpStatus.OK).body(service.getTrackingActivityByDay(startDate, endDate, AppUtils.getAuthenticatedUser()));
    }

    @PatchMapping(value = "/update")
    public ResponseEntity<Void> updateTrackingActivity(@RequestBody @Valid final TrackingRecordUpdate inputs){
        service.updateTrackingActivity(inputs);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
