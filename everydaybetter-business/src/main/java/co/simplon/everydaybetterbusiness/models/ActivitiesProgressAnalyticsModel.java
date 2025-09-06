package co.simplon.everydaybetterbusiness.models;

public record ActivitiesProgressAnalyticsModel(Long activityId, String activityName, Boolean positive,
        Progress progress) {
    public record Progress(double done, double missed, double untracked) {
    }
}
