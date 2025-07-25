package co.simplon.everydaybetterbusiness.dtos;

import co.simplon.everydaybetterbusiness.validators.UniqueTrackingLogCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@UniqueTrackingLogCreate
public record TrackingLogCreate(@NotBlank String activityId, @NotNull LocalDate trackedDate, @NotNull Boolean done) {
}
//Note: if not use annotation validation, service will call insert into in DB, we will have error sql, because it is not be accepted by constraint db
