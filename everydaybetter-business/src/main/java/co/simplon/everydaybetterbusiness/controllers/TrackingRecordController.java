package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.common.AppUtils;
import co.simplon.everydaybetterbusiness.dtos.TrackingRecordDto;
import co.simplon.everydaybetterbusiness.models.TrackingRecordModel;
import co.simplon.everydaybetterbusiness.services.TrackingRecordForUserActivityService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tracking-record")
@SecurityRequirement(name = "bearerAuth")
public class TrackingRecordController {
    private final TrackingRecordForUserActivityService trackingRecordForUserActivityService;

    public TrackingRecordController(TrackingRecordForUserActivityService trackingRecordForUserActivityService) {
        this.trackingRecordForUserActivityService = trackingRecordForUserActivityService;
    }

    //choix between return void or an object TrackingRecordDto => update direct state, it's better to return an object
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrackingRecordModel> saveUserActivityRecord(@RequestBody @Valid final TrackingRecordDto inputs){
        return ResponseEntity.status(HttpStatus.CREATED).body(trackingRecordForUserActivityService.saveTrackingRecordForUserActivity(inputs, AppUtils.getAuthenticatedUser()));
    }

}
