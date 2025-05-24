package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.entities.TrackingRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TrackingRecordService {
    TrackingRecord save(TrackingRecord trackingRecord);

    List<Map<LocalDate, Boolean>> findTrackingByDayList(Long activityId, LocalDate startDate, LocalDate endDate);
}
