package dstu.inspection.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserAlreadyExistsValidator.class)
@Documented
public @interface UserAlreadyExists {
    String message() default "Такой пользователь уже существует";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}