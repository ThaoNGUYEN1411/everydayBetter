package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.dtos.TrackingLogCreate;
import co.simplon.everydaybetterbusiness.models.ActivityTrackingLogModel;
import co.simplon.everydaybetterbusiness.models.TrackingLogModel;

import java.time.LocalDate;
import java.util.List;

public interface UserActivityTrackingLogService {
    TrackingLogModel saveTrackingLogForUserActivity(TrackingLogCreate inputs, String email);

    List<ActivityTrackingLogModel> getTrackingActivityByDay(LocalDate startDate, LocalDate endDate, String email);
}
