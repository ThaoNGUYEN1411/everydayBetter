package co.simplon.everydaybetterbusiness.models;

import java.time.LocalDate;

public record TrackingRecordModel(
       String activityId,LocalDate trackedDate, Boolean done
) {
}
