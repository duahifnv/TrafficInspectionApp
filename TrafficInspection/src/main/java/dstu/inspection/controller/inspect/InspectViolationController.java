package dstu.inspection.controller.inspect;

import dstu.inspection.dto.ViolationDto;
import dstu.inspection.entity.Violation;
import dstu.inspection.entity.info.VehiclesInfo;
import dstu.inspection.service.InfoService;
import dstu.inspection.service.ViolationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/inspect")
@RequiredArgsConstructor
public class InspectViolationController {
    private final ViolationService violationService;
    private final InfoService infoService;
    @GetMapping
    public String landingPage() {
        return "redirect:/inspect/violations";
    }
    @GetMapping("/violations")
    public String violationsPage(Model model) {
        model.addAttribute("violations", infoService.findAllViolations());
        return "pages/employee/all_violations";
    }
    @GetMapping("/violations/new")
    public String newViolationForm(Model model) {
        model.addAttribute("violation", new ViolationDto());
        return "pages/employee/new_violation_form";
    }
    @PostMapping("/violations/new")
    public String sendViolationForm(@ModelAttribute(name = "violation") @Valid ViolationDto violationDto,
                                    BindingResult result, Model model) throws ParseException {
        if (result.hasErrors()) {
            return "pages/employee/new_violation_form";
        }
        String registrationCode = violationDto.getRegistrationCode();
        VehiclesInfo vehicle = infoService.findVehicleInfoByRegistrationCode(registrationCode);
        if (vehicle == null) {
            String errorMessage = "Не найдено ТС с номером: " + registrationCode;
            FieldError error = new FieldError("violation",
                    "registrationCode", errorMessage);
            result.addError(error);
            model.addAttribute("violation", new ViolationDto());
            return "pages/employee/new_violation_form";
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfViolation = dateFormat.parse(violationDto.getDateOfViolation());
        Violation violation = Violation.builder()
                                        .registrationCode(violationDto.getRegistrationCode())
                                        .fineId(violationDto.getFineId())
                                        .dateOfViolation(dateOfViolation)
                                        .build();
        violationService.save(violation);
        return "redirect:/inspect/violations";
    }

}
