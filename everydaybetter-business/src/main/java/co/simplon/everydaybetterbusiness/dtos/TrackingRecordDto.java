package co.simplon.everydaybetterbusiness.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TrackingRecordDto(@NotBlank String activityId,@NotBlank LocalDate trackedDate,@NotNull Boolean done) {
}
