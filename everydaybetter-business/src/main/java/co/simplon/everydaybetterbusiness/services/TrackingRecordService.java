package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.entities.TrackingRecord;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Map;

public interface TrackingRecordService {
    TrackingRecord save(TrackingRecord trackingRecord);

    Map<LocalDate, Boolean> findTrackingByDayList(Long activityId, LocalDate startDate, LocalDate endDate);

    TrackingRecord findTrackingRecordByActivityIdAndTrackedDate(@NotBlank String s, @NotNull LocalDate localDate);
}
