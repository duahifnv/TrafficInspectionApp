package dstu.inspection.dto;

import dstu.inspection.validation.ValidEmail;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class UserDto {
    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @NotEmpty
    @ValidEmail(message = "Email должен соответствовать формату")
    private String email;
    @NotNull
    @NotEmpty
    @Size(min = 6, message = "Пароль должен быть длиной минимум 6 символов")
    private String password;
}
