package dstu.inspection.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public String controlPage(Model model, Principal principal) {
        model.addAttribute("admin_name", principal.getName());
        return "pages/admin";
    }
}
