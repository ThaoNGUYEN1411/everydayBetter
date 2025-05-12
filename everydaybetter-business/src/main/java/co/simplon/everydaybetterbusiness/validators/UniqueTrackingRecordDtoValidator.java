package co.simplon.everydaybetterbusiness.validators;

import co.simplon.everydaybetterbusiness.dtos.TrackingRecordDto;
import co.simplon.everydaybetterbusiness.repositories.TrackingRecordRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class UniqueTrackingRecordDtoValidator implements ConstraintValidator<UniqueTrackingRecordDto, TrackingRecordDto> {
    private final TrackingRecordRepository trackingRecordRepository;

    public UniqueTrackingRecordDtoValidator(TrackingRecordRepository trackingRecordRepository) {
        this.trackingRecordRepository = trackingRecordRepository;
    }

    @Override
    public void initialize(UniqueTrackingRecordDto constraintAnnotation){
        // TODO document why this method is empty
    }

    @Override
    public boolean isValid(final TrackingRecordDto dto, ConstraintValidatorContext context){
        final Long idActivity = Long.valueOf(dto.activityId());
        final LocalDate trackedDate = dto.trackedDate();
        if (trackedDate == null){
            return true;
        }
        return !trackingRecordRepository.existsByActivityIdAndTrackedDate(idActivity, trackedDate);
    }
}
