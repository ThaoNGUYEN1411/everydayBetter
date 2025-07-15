package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.dtos.TrackingLogUpdate;
import co.simplon.everydaybetterbusiness.entities.TrackingLog;
import co.simplon.everydaybetterbusiness.models.ActivityTrackingLogModel;
import co.simplon.everydaybetterbusiness.view.TrackingSummaryView;

import java.time.LocalDate;
import java.util.List;

public interface TrackingLogService {
    TrackingLog save(TrackingLog trackingLog);

    List<ActivityTrackingLogModel.TrackingLogDto> findAllTrackingLogByActivityIdAndPeriodTime(Long activityId, LocalDate startDate, LocalDate endDate);

    void updateTrackingActivity(TrackingLogUpdate trackingLogUpdate);

    void deleteById(Long id, String email);

    void deleteAllByActivityId(Long id);

    TrackingSummaryView findTrackingSummaryByActivityIdAndPeriod(Long activityId, LocalDate startDate, LocalDate endDate);

    boolean existsTrackingLogByActivityIdAndPeriod(Long activityId, LocalDate startDate, LocalDate endDate);
}
