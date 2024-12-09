package dstu.inspection.controller;

import dstu.inspection.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class LandingController {
    private final InfoService infoService;
    @GetMapping
    public String landingPage() {
        return "landing";
    }
    @GetMapping("/categories")
    public String categoryList(Model model) {
        model.addAttribute("categories",
                infoService.findAllCategories());
        return "categories";
    }
    @GetMapping("/fines")
    public String fineList(Model model) {
        model.addAttribute("fines",
                infoService.findAllFines());
        return "fines";
    }
    @GetMapping("/employees")
    public String employeeList(Model model) {
        model.addAttribute("employees",
                infoService.findAllEmployees());
        return "employees";
    }
    @GetMapping("/departments")
    public String departmentList(Model model) {
        model.addAttribute("departments",
                infoService.findAllDepartments());
        return "departments";
    }
}
