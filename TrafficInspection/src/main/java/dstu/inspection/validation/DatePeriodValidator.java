package dstu.inspection.validation;

import dstu.inspection.service.InfoService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

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
    private final InfoService infoService;
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

            if (startField.get(obj) == null || endField.get(obj) == null) {
                return true;
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse((String) startField.get(obj));
            Date endDate = dateFormat.parse((String) endField.get(obj));
            return startDate.before(endDate);
        }
        catch (Exception e) {
            System.out.println("@DatePeriod: " + e.getMessage());
            return false;
        }
    }
}
