package dstu.inspection.controller;

import dstu.inspection.entity.info.LicensesInfo;
import dstu.inspection.entity.info.VehiclesInfo;
import dstu.inspection.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/me")
@RequiredArgsConstructor
public class UserController {
    private final InfoService infoService;
    @GetMapping
    public String profilePage() {
        return "redirect:/me/license";
    }
    @GetMapping("/license")
    public String licensePage() {
        return "pages/license";
    }
    @GetMapping("/license/new")
    public String licenseForm() {
        return "New license form";
    }
    @PostMapping("/license/new")
    public String sendLicenseForm(@ModelAttribute LicensesInfo licensesInfo) {
        // infoService.addLicense(licensesInfo);
        return "redirect:/license";
    }
    @GetMapping("/vehicles")
    public String vehiclesPage(Model model, Principal principal) {
        List<VehiclesInfo> userVehicles = infoService.findVehiclesByPhone(principal.getName());
        model.addAttribute("vehicles",
                userVehicles);
        return "pages/vehicles";
    }
}
