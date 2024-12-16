package dstu.inspection.controller.guest;

import dstu.inspection.dto.user.UserDto;
import dstu.inspection.service.UserService;
import dstu.inspection.service.UserAlreadyExistAuthenticationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {
    private final SecurityContextLogoutHandler logoutHandler =
            new SecurityContextLogoutHandler();
    private final UserService userService;
    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "pages/registration";
    }
    @PostMapping("/registration")
    public String sendRegistrationForm(@ModelAttribute("user") @Valid UserDto userDto,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "pages/registration";
        }
        try {
            userService.save(userDto);
        }
        catch (UserAlreadyExistAuthenticationException e) {
            model.addAttribute("usernameError", e.getMessage());
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
