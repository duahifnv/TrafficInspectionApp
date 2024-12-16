package dstu.inspection.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DatePeriodValidator.class)
@Documented
public @interface DatePeriod {
    String message() default "Начальная дата должна быть меньше конечной даты";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String startDate();
    String endDate();
}
