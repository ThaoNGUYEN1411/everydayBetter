package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.dtos.TrackingRecordCreate;
import co.simplon.everydaybetterbusiness.models.ActivityTrackingRecordModel;
import co.simplon.everydaybetterbusiness.models.TrackingRecordModel;

import java.time.LocalDate;
import java.util.List;

public interface UserActivityTrackingRecordService {
    TrackingRecordModel saveTrackingRecordForUserActivity(TrackingRecordCreate inputs, String email);

    List<ActivityTrackingRecordModel> getTrackingActivityByDay(LocalDate startDate, LocalDate endDate, String email);
}
