package dstu.inspection.controller.inspect;

import dstu.inspection.dto.ViolationDto;
import dstu.inspection.entity.Violation;
import dstu.inspection.entity.info.VehiclesInfo;
import dstu.inspection.entity.info.ViolationsInfo;
import dstu.inspection.mapper.ViolationMapper;
import dstu.inspection.service.InfoService;
import dstu.inspection.service.ViolationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
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
    private final ViolationMapper violationMapper;
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
    public String newViolationForm(ViolationDto violationDto) {
        return "pages/employee/new_violation_form";
    }
    @PostMapping("/violations/new")
    public String sendViolationForm(@Validated ViolationDto violationDto,
                                    BindingResult result) {
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
            return "pages/employee/new_violation_form";
        }
        Violation violation = violationMapper.dtoToModel(violationDto);
        violationService.save(violation);
        return "redirect:/inspect/violations";
    }
    @GetMapping("/violations/edit/{id}")
    public String violationEditForm(@PathVariable Long id, Model model) {
        Violation violation = violationService.findById(id);
        if (violation == null) {
            return "redirect:/inspect/violations";
        }
        ViolationDto violationDto = violationMapper.modelToDto(violation);
        model.addAttribute("violationDto", violationDto);
        return "pages/employee/edit_violation_form";
    }
    @PostMapping("/violations/edit/{id}")
    public String sendViolationEditForm(@Validated ViolationDto violationDto,
                                        @PathVariable Long id) {
        Violation violation = violationMapper.dtoToModel(violationDto);
        violation.setViolationId(id);
        violationService.save(violation);
        return "redirect:/inspect/violations";
    }
    @PostMapping("/violations/{id}")
    public String  deleteViolation(@PathVariable Long id) {
        if (violationService.findById(id) != null) {
            violationService.deleteById(id);
        }
        return "redirect:/inspect/violations";
    }
}
