package co.simplon.everydaybetterbusiness.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record ActivityTrackingRecordModel(Long activityId, String activityName, List<Map<LocalDate, Boolean>> trackingByDayList) {
}
