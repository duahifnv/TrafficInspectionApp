package dstu.inspection.dto.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LicenseDto {
    @NotNull
    @Min(value = 1, message = "Код удостоверения должен быть больше нуля")
    private Long licenseId;
    @NotNull
    @Min(value = 1, message = "Код подразделения должен быть больше нуля")
    private Long departmentId;
}
