package co.simplon.everydaybetterbusiness.view;

public record ProgressAnalyticsView(
        long countDone,
        long countNotDone,
        long countNotTrack,
        long total
) {
}
