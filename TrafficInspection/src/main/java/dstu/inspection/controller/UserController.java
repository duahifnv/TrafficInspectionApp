package dstu.inspection.controller;

import dstu.inspection.entity.info.LicensesInfo;
import dstu.inspection.entity.info.VehiclesInfo;
import dstu.inspection.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String licensePage(Model model, Principal principal) {
        LicensesInfo userLicense = infoService.findLicenseByPhone(principal.getName());
        model.addAttribute("license",
                userLicense);
        return "pages/license";
    }
    @GetMapping("/vehicles")
    public String vehiclesPage(Model model, Principal principal) {
        List<VehiclesInfo> userVehicles = infoService.findVehiclesByPhone(principal.getName());
        model.addAttribute("vehicles",
                userVehicles);
        return "pages/vehicles";
    }
}
