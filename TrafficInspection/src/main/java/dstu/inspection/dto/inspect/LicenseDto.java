package dstu.inspection.dto.inspect;

import dstu.inspection.validation.DatePeriod;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@DatePeriod(startDate = "dateOfIssue", endDate = "dateOfExpiry")
@Data
public class LicenseDto {
    private Long licenseId;
    @NotNull(message = "Заполните поле")
    @Min(value = 1, message = "Код водителя должен быть больше нуля")
    private Long driverId;
    @NotNull(message = "Заполните поле")
    @Min(value = 1, message = "Код подразделения должен быть больше нуля")
    private Long departmentId;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dateOfIssue;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dateOfExpiry;
}
