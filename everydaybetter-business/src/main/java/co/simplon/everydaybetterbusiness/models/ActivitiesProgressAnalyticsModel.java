package co.simplon.everydaybetterbusiness.models;

public record ActivitiesProgressAnalyticsModel(String activityId, String activityName, Progress progress) {
    public record Progress(Long done, Long missed, Long untracked) {
    }
}
