package dstu.inspection.dto;

import jakarta.persistence.Temporal;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ViolationDto {
    @NotBlank
    private String registrationCode;
    @NotNull
    private Long fineId;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dateOfViolation;
}
