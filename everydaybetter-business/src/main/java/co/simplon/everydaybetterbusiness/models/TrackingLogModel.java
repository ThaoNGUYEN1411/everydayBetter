package co.simplon.everydaybetterbusiness.models;

import java.time.LocalDate;

public record TrackingLogModel(
       String activityId,LocalDate trackedDate, Boolean done
) {
}
