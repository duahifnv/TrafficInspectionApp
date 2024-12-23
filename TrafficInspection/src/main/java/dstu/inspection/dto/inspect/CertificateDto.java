package dstu.inspection.dto.inspect;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CertificateDto {
    private Long certificateId;
    @NotBlank
    private String registrationCode;
    @NotNull(message = "Заполните поле")
    @Min(value = 1, message = "Код паспорта должен быть больше нуля")
    private Long passportId;
    @NotNull(message = "Заполните поле")
    @Min(value = 1, message = "Код водителя должен быть больше нуля")
    private Long driverId;
    @NotNull(message = "Заполните поле")
    @Min(value = 1, message = "Код категории должен быть больше нуля")
    private Long categoryId;
    @NotNull(message = "Заполните поле")
    @Min(value = 1, message = "Код подразделения должен быть больше нуля")
    private Long departmentId;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dateOfRegistration;
}
