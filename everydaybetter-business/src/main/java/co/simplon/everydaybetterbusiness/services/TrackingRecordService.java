package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.dtos.TrackingRecordDelete;
import co.simplon.everydaybetterbusiness.dtos.TrackingRecordUpdate;
import co.simplon.everydaybetterbusiness.entities.TrackingRecord;

import java.time.LocalDate;
import java.util.Map;

public interface TrackingRecordService {
    TrackingRecord save(TrackingRecord trackingRecord);

    Map<LocalDate, Boolean> findTrackingByDayList(Long activityId, LocalDate startDate, LocalDate endDate);

    void updateTrackingActivity(TrackingRecordUpdate inputs);

    void deleteTrackingActivity(TrackingRecordDelete trackingRecordDelete);
}
