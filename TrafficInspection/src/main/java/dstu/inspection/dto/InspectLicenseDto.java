package dstu.inspection.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InspectLicenseDto {
    private long driverId;
    @NotNull
    @NotEmpty
    @Min(value = 1, message = "Код подразделения должен быть ненулевым")
    private long departmentId;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dateOfIssue;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dateOfExpiry;
}
