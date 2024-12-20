package dstu.inspection.dto.user;

import dstu.inspection.validation.UserAlreadyExists;
import dstu.inspection.validation.ValidEmail;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    @NotEmpty(message = "не должен быть пустым")
    private String username;
    @ValidEmail(message = "Email должен соответствовать формату")
    private String email;
    @Size(min = 6, message = "Пароль должен быть длиной минимум 6 символов")
    private String password;
}
