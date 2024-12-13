package dstu.inspection.controller.guest;

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
        return "pages/guest/landing";
    }
    @GetMapping("/categories")
    public String categoryList(Model model) {
        model.addAttribute("categories",
                infoService.findAllCategories());
        return "pages/guest/categories";
    }
    @GetMapping("/fines")
    public String fineList(Model model) {
        model.addAttribute("fines",
                infoService.findAllFines());
        return "pages/guest/fines";
    }
    @GetMapping("/employees")
    public String employeeList(Model model) {
        model.addAttribute("employees",
                infoService.findAllEmployees());
        return "pages/guest/employees";
    }
    @GetMapping("/departments")
    public String departmentList(Model model) {
        model.addAttribute("departments",
                infoService.findAllDepartments());
        return "pages/guest/departments";
    }
    @GetMapping("/getFines")
    public String violationList(@RequestParam("code")
                                    String registrationCode, Model model) {
        model.addAttribute("registrationCode", registrationCode);
        model.addAttribute("violations",
                infoService.findViolationsByRegistrationCode(registrationCode));
        return "pages/guest/guest_violations";
    }
}
