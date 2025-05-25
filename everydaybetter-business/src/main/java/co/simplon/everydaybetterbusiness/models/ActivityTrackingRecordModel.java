package co.simplon.everydaybetterbusiness.models;

import java.time.LocalDate;
import java.util.Map;

public record ActivityTrackingRecordModel(Long activityId, String activityName, Map<LocalDate, Boolean> trackingByDay) {
}
