package dstu.inspection.controller.user;

import dstu.inspection.dto.user.LicenseDto;
import dstu.inspection.entity.License;
import dstu.inspection.entity.Violation;
import dstu.inspection.entity.info.CertificateInfo;
import dstu.inspection.entity.info.VehiclesInfo;
import dstu.inspection.entity.info.ViolationsInfo;
import dstu.inspection.entity.security.User;
import dstu.inspection.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/me")
@RequiredArgsConstructor
public class UserController {
    private final DepartmentService departmentService;
    private final LicenseService licenseService;
    private final UserService userService;
    private final ViolationService violationService;
    private final VehicleService vehicleService;
    private final CertificateService certificateService;
    @GetMapping
    public String profilePage() {
        return "redirect:/me/license";
    }
    @GetMapping("/license")
    public String licenseTab() {
        return "pages/user/my_license";
    }
    @GetMapping("/license/new")
    public String licenseForm(LicenseDto licenseDto, HttpSession session, Model model) {
        if (session.getAttribute("license") != null) {
            return "redirect:/me/license";
        }
        model.addAttribute("departmentList", departmentService.findAll());
        return "pages/user/license_form";
    }
    @PostMapping("/license/new")
    public String sendLicenseForm(@Validated LicenseDto licenseDto,
                                  BindingResult result, Model model,
                                  Principal principal, HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("departmentList", departmentService.findAll());
            return "pages/user/license_form";
        }
        License license = licenseService.findById(licenseDto.getLicenseId());
        if (license == null ||
                !Objects.equals(license.getDepartmentId(), licenseDto.getDepartmentId())) {
            String errorMessage = "Не найдено удостоверения с такими данными";
            FieldError error = new FieldError("licenseDto",
                    "licenseId", errorMessage);
            result.addError(error);
            model.addAttribute("departmentList", departmentService.findAll());
            return "pages/user/license_form";
        }
        Long licenseId = license.getLicenseId();
        User user = userService.findByUsername(principal.getName());
        user.setLicenseId(licenseId);
        userService.updateUser(user);
        session.setAttribute("license", licenseService.findLicenseInfoById(licenseId));
        return "redirect:/me/license";
    }
    @PostMapping("/license/delete")
    public String deleteLicense(Principal principal, HttpSession session) {
        User user = userService.findByUsername(principal.getName());
        if (user.getLicenseId() != null) {
            user.setLicenseId(null);
            userService.updateUser(user);
            session.setAttribute("license", null);
        }
        return "redirect:/me/license";
    }
    @GetMapping("/vehicles")
    public String vehiclesTab(Model model, Principal principal) {
        List<VehiclesInfo> userVehicles = vehicleService.findAllVehiclesInfoByUsername(principal.getName());
        model.addAttribute("vehicles",
                userVehicles);
        return "pages/user/my_vehicles";
    }
    @GetMapping("/violations")
    public String violationsTab(Model model, Principal principal) {
        List<ViolationsInfo> violations = violationService.findViolationsInfoByUsername(principal.getName());
        model.addAttribute("violations",
                violations);
        return "pages/user/my_violations";
    }
    @PostMapping("/violations/{id}")
    public String payoffViolation(@PathVariable Long id) {
        Violation violation = violationService.findById(id);
        if (violation == null) {
            return "redirect:/me/violations";
        }
        violation.setDateOfPayment(Date.from(Instant.now()));
        violationService.save(violation);
        return "redirect:/me/violations";
    }
    @GetMapping("/certificates")
    public String certificatesTab(Model model, Principal principal) {
        List<CertificateInfo> certificateInfos =
                certificateService.findAllCertificatesInfoByUsername(principal.getName());
        model.addAttribute("certificates", certificateInfos);
        return "pages/user/my_certificates";
    }
}
