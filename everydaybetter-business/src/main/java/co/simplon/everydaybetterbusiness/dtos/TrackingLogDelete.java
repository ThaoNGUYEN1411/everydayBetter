package co.simplon.everydaybetterbusiness.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TrackingLogDelete(@NotBlank String activityId, @NotNull LocalDate trackedDate) {
}
