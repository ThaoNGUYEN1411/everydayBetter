package co.simplon.everydaybetterbusiness.validators;

import co.simplon.everydaybetterbusiness.common.AppUtils;
import co.simplon.everydaybetterbusiness.dtos.ActivityUpdate;
import co.simplon.everydaybetterbusiness.entities.User;
import co.simplon.everydaybetterbusiness.repositories.ActivityRepository;
import co.simplon.everydaybetterbusiness.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ActivityUpdateUniqueValidator implements ConstraintValidator<ActivityUpdateUnique, ActivityUpdate> {
    private final ActivityRepository repository;
    private final AppUtils utils;
    private final UserRepository userRepository;

    public ActivityUpdateUniqueValidator(ActivityRepository repository, AppUtils utils, UserRepository userRepository) {
        this.repository = repository;
        this.utils = utils;
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(ActivityUpdateUnique constraintAnnotation) {
    }

    @Override
    public boolean isValid(final ActivityUpdate activityUpdate, ConstraintValidatorContext context) {
        final String name = activityUpdate.name();
        final String email = utils.getAuthenticatedUser();
        final Long userId = userRepository.findByEmailIgnoreCase(email).map(User::getId)
                .orElse(null);
        final Long id = ValidatorUtils.pathVariableAsLong("id");
        if (name == null || userId == null) {
            return true;
        }
        return !repository.existsByNameIgnoreCaseAndUserIdAndIdNot(name, userId, id);
    }
}
