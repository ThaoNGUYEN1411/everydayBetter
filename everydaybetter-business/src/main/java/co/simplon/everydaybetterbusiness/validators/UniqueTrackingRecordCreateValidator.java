package co.simplon.everydaybetterbusiness.validators;

import co.simplon.everydaybetterbusiness.dtos.TrackingRecordCreate;
import co.simplon.everydaybetterbusiness.repositories.TrackingRecordRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class UniqueTrackingRecordCreateValidator implements ConstraintValidator<UniqueTrackingRecordCreate, TrackingRecordCreate> {
    private final TrackingRecordRepository trackingRecordRepository;

    public UniqueTrackingRecordCreateValidator(TrackingRecordRepository trackingRecordRepository) {
        this.trackingRecordRepository = trackingRecordRepository;
    }

    @Override
    public void initialize(UniqueTrackingRecordCreate constraintAnnotation){
        // TODO document why this method is empty
    }

    @Override
    public boolean isValid(final TrackingRecordCreate dto, ConstraintValidatorContext context){
        final Long idActivity = Long.valueOf(dto.activityId());
        final LocalDate trackedDate = dto.trackedDate();
        if (trackedDate == null){
            return true;
        }
        return !trackingRecordRepository.existsByActivityIdAndTrackedDate(idActivity, trackedDate);
    }
}
