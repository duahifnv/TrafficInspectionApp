package dstu.inspection.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLicenseDto {
    @NotEmpty
    @Min(value = 1, message = "Код удостоверения должен быть ненулевым")
    private Long licenseId;
    @NotEmpty
    @Min(value = 1, message = "Код подразделения должен быть ненулевым")
    private Long departmentId;
}
