package dstu.inspection.validation;

import dstu.inspection.service.VehicleService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class DatePeriodValidator implements ConstraintValidator<DatePeriod, Object> {
    // Названия полей, которые указываются в теле аннотации
    private String startDateFieldName;
    private String endDateFieldName;
    private final VehicleService infoService;
    @Override
    public void initialize(DatePeriod constraintAnnotation) {
        this.startDateFieldName = constraintAnnotation.startDate();
        this.endDateFieldName = constraintAnnotation.endDate();
    }
    // Вызывается при проверке (Runtime)
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        try {
            // Получаем объявленные поля объекта по их именам
            Field startField = obj.getClass().getDeclaredField(startDateFieldName);
            Field endField = obj.getClass().getDeclaredField(endDateFieldName);
            startField.setAccessible(true);
            endField.setAccessible(true);

            if (startField.get(obj) == null ||
                endField.get(obj) == null ||
                startField.get(obj).toString().isBlank() ||
                endField.get(obj).toString().isBlank()) {
                return true;
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse((String) startField.get(obj));
            Date endDate = dateFormat.parse((String) endField.get(obj));
            return !startDate.after(endDate);
        }
        catch (Exception e) {
            throw new RuntimeException("@DatePeriod: " + e.getMessage());
        }
    }
}
