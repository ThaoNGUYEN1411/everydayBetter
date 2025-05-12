package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.dtos.TrackingRecordDto;
import co.simplon.everydaybetterbusiness.models.TrackingRecordModel;

public interface TrackingRecordForUserActivityService {
    TrackingRecordModel saveTrackingRecordForUserActivity(TrackingRecordDto inputs, String email);
}
