package dstu.inspection.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    private final SecurityContextLogoutHandler logoutHandler =
            new SecurityContextLogoutHandler();
    @GetMapping("/login")
    public String loginForm() {
        return "pages/login";
    }
    @GetMapping("/logout")
    public String logoutForm(Authentication authentication,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        logoutHandler.logout(request, response, authentication);
        return "redirect:/";
    }
}
