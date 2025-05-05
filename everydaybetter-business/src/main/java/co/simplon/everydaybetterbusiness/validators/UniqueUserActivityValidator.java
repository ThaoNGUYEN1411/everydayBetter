package co.simplon.everydaybetterbusiness.validators;

import co.simplon.everydaybetterbusiness.common.Utils;
import co.simplon.everydaybetterbusiness.dtos.ActivityCreate;
import co.simplon.everydaybetterbusiness.entities.User;
import co.simplon.everydaybetterbusiness.repositories.ActivityRepository;
import co.simplon.everydaybetterbusiness.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUserActivityValidator implements ConstraintValidator<UniqueUserActivity, ActivityCreate> {
    private final ActivityRepository repository;
    private final Utils utils;
    private final UserRepository userRepository;


    public UniqueUserActivityValidator(ActivityRepository repository, Utils utils, UserRepository userRepository) {
        this.repository = repository;
        this.utils = utils;
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UniqueUserActivity constraintAnnotation){
    }

    @Override
    public boolean isValid(final ActivityCreate activityCreate, ConstraintValidatorContext context){
        final String name = activityCreate.name();
        final String email = utils.getAuthenticatedUser();
        final Long userId = userRepository.findByEmailIgnoreCase(email).map(User::getId)
                .orElse(null);
        if (name == null || userId == null){
            return  true;
        }
        return !repository.existsByNameIgnoreCaseAndUserId(name, userId);
    }
}
