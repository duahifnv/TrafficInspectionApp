package dstu.inspection.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/me")
@RequiredArgsConstructor
public class UserController {
    @GetMapping
    public String profilePage(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        return "profile";
    }
}
