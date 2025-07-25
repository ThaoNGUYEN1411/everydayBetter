package co.simplon.everydaybetterbusiness.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TrackingLogUpdate(@NotBlank String activityId, @NotNull LocalDate trackedDate, @NotNull Boolean done) {
}
