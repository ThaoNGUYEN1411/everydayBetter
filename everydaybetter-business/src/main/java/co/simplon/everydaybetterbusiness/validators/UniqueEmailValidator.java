package co.simplon.everydaybetterbusiness.validators;

import co.simplon.everydaybetterbusiness.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final UserService service;

    public UniqueEmailValidator(UserService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(final String email, ConstraintValidatorContext context){
        if(email == null){ // handle null separately
            return true;
        }
        return !service.existsByEmailIgnoreCase(email);
    }
}
