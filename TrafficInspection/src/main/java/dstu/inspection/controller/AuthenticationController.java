package dstu.inspection.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {
    private final SecurityContextLogoutHandler logoutHandler =
            new SecurityContextLogoutHandler();
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
