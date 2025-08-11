package co.simplon.everydaybetterbusiness.validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ActivityUpdateUniqueValidator.class)
public @interface ActivityUpdateUnique {
    String message() default "Unique activity by user";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
