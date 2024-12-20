package dstu.inspection.dto;

import dstu.inspection.validation.DatePeriod;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@DatePeriod(startDate = "dateOfViolation", endDate = "dateOfPayment")
@Data
@Builder
public class ViolationDto {
    private Long violationId;
    @NotBlank
    private String registrationCode;
    @NotNull(message = "Заполните поле")
    @Min(value = 1, message = "Код штрафа должен быть больше нуля")
    private Long fineId;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dateOfViolation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dateOfPayment;
}
