package co.simplon.everydaybetterbusiness.models;

import java.time.LocalDate;
import java.util.List;

public record ActivityTrackingLogModel(Long activityId, String activityName, List<TrackingLogDto> listTrackingLog) {
    public record TrackingLogDto(
            Long id,
            LocalDate date,
            Boolean done
    ){}
}
