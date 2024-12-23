package dstu.inspection.controller.guest;

import dstu.inspection.dto.user.UserDto;
import dstu.inspection.service.UserService;
import dstu.inspection.validation.UserAlreadyExistAuthenticationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {
    private final SecurityContextLogoutHandler logoutHandler =
            new SecurityContextLogoutHandler();
    private final UserService userService;
    @GetMapping("/registration")
    public String registrationPage(UserDto userDto) {
        return "pages/registration";
    }
    @PostMapping("/registration")
    public String sendRegistrationForm(@Validated UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            return "pages/registration";
        }
        try {
            userService.save(userDto);
        }
        catch (UserAlreadyExistAuthenticationException e) {
            FieldError error = new FieldError("userDto",
                    "username", e.getMessage());
            result.addError(error);
            return "pages/registration";
        }
        return "redirect:/login";
    }
    // Получаем страницу авторизации
    @GetMapping("/login")
    public String loginPage() {
        return "pages/login";
    }
    // Сабмитаем выход из учетной записи
    @GetMapping("/logout")
    public String logoutHandle(HttpServletRequest request,
                               HttpServletResponse response,
                               Authentication authentication) {
        logoutHandler.logout(request, response, authentication);
        return "redirect:/";
    }
}
