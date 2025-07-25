package co.simplon.everydaybetterbusiness.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //fait partie de Java et est utilisée pour définir la durée de vie (persistence) d'une annotation personnalisée.
@Target({ElementType.FIELD}) //This custom annotation can be applied to fields
@Documented //This makes the annotation appear in Javadoc.
@Constraint(validatedBy = UniqueEmailValidator.class) //Constraint of the logic for checking unique email
public @interface UniqueEmail {
    String message() default "Email is already exists"; // Default error message when validation fails

    Class<?>[] groups() default {}; //Used for validation groups (optional, rarely used in simple cases) => regrouper des errors

    Class<? extends Payload>[] payload() default {}; //payload: Can carry metadata info (also optional)
}
/*
@Retention indique à quel moment une annotation est conservée (ou disponible) dans le cycle de vie du programme Java.
Runtime: lue au moment de l’exécution,
signifie que ton annotation personnalisée sera disponible pendant l'exécution du programme
 */
