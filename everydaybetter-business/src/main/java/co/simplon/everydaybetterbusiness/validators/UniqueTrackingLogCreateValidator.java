package co.simplon.everydaybetterbusiness.validators;

import co.simplon.everydaybetterbusiness.dtos.TrackingLogCreate;
import co.simplon.everydaybetterbusiness.repositories.TrackingLogRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class UniqueTrackingLogCreateValidator implements ConstraintValidator<UniqueTrackingLogCreate, TrackingLogCreate> {
    private final TrackingLogRepository trackingLogRepository;

    public UniqueTrackingLogCreateValidator(TrackingLogRepository trackingLogRepository) {
        this.trackingLogRepository = trackingLogRepository;
    }

    @Override
    public void initialize(UniqueTrackingLogCreate constraintAnnotation){
        // TODO document why this method is empty
    }

    @Override
    public boolean isValid(final TrackingLogCreate dto, ConstraintValidatorContext context){
        final Long idActivity = Long.valueOf(dto.activityId());
        final LocalDate trackedDate = dto.trackedDate();
        if (trackedDate == null){
            return true;
        }
        return !trackingLogRepository.existsByActivityIdAndTrackedDate(idActivity, trackedDate);
    }
}
