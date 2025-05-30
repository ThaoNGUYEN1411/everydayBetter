package co.simplon.everydaybetterbusiness.view;

import java.time.LocalDate;

public interface TrackingView {
    Long getId();
    LocalDate getTrackedDate();
    Boolean getDone();
}
