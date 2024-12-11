package dstu.inspection.controller;

import dstu.inspection.entity.info.LicensesInfo;
import dstu.inspection.service.LicensesInfoService;
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
    private final LicensesInfoService licensesInfoService;
    @GetMapping
    public String profilePage() {
        return "redirect:/me/license";
    }
    @GetMapping("/license")
    public String licensePage(Model model, Principal principal) {
        LicensesInfo userLicense = licensesInfoService.findByPhone(principal.getName());
        model.addAttribute("license",
                userLicense);
        return "pages/license";
    }
}
