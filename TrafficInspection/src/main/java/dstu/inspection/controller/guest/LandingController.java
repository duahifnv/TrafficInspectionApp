package dstu.inspection.controller.guest;

import dstu.inspection.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class LandingController {
    private final CategoryService categoryService;
    private final FineService fineService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final VehicleService vehicleService;
    private final ViolationService violationService;
    @GetMapping
    public String landingPage() {
        return "pages/guest/landing";
    }
    @GetMapping("/categories")
    public String categoryList(Model model) {
        model.addAttribute("categories",
                categoryService.findAllCategoriesInfo());
        return "pages/guest/categories";
    }
    @GetMapping("/fines")
    public String fineList(Model model) {
        model.addAttribute("fines",
                fineService.findAllFinesInfo());
        return "pages/guest/fines";
    }
    @GetMapping("/employees")
    public String employeeList(Model model) {
        model.addAttribute("employees",
                employeeService.findAllEmployeesInfo());
        return "pages/guest/employees";
    }
    @GetMapping("/departments")
    public String departmentList(Model model) {
        model.addAttribute("departments",
                departmentService.findAll());
        return "pages/guest/departments";
    }
    @GetMapping("/getFines")
    public String violationList(@RequestParam("code")
                                    String registrationCode, Model model) {
        if (vehicleService.findVehicleInfoByRegistrationCode(registrationCode) == null) {
            String errorMessage = "Не найдено ТС с номером " + registrationCode;
            model.addAttribute("registrationCodeError", errorMessage);
            return "pages/guest/landing";
        }
        model.addAttribute("registrationCode", registrationCode);
        model.addAttribute("violations",
                violationService.findViolationsInfoByRegistrationCode(registrationCode));
        return "pages/guest/guest_violations";
    }
}
