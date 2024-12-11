package dstu.inspection.controller;

import dstu.inspection.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class LandingController {
    private final InfoService infoService;
    @GetMapping
    public String landingPage() {
        return "pages/landing";
    }
    @GetMapping("/categories")
    public String categoryList(Model model) {
        model.addAttribute("categories",
                infoService.findAllCategories());
        return "pages/categories";
    }
    @GetMapping("/fines")
    public String fineList(Model model) {
        model.addAttribute("fines",
                infoService.findAllFines());
        return "pages/fines";
    }
    @GetMapping("/employees")
    public String employeeList(Model model) {
        model.addAttribute("employees",
                infoService.findAllEmployees());
        return "pages/employees";
    }
    @GetMapping("/departments")
    public String departmentList(Model model) {
        model.addAttribute("departments",
                infoService.findAllDepartments());
        return "pages/departments";
    }
    @GetMapping("/getFines")
    public String violationList(@RequestParam("code")
                                    String registrationCode, Model model) {
        model.addAttribute("registrationCode", registrationCode);
        model.addAttribute("violations",
                infoService.findByRegistrationCode(registrationCode));
        return "pages/violations";
    }
}
