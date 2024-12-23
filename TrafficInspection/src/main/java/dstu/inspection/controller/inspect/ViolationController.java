package dstu.inspection.controller.inspect;

import dstu.inspection.dto.ViolationDto;
import dstu.inspection.entity.Violation;
import dstu.inspection.entity.info.VehiclesInfo;
import dstu.inspection.mapper.ViolationMapper;
import dstu.inspection.service.FineService;
import dstu.inspection.service.VehicleService;
import dstu.inspection.service.ViolationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inspect")
@RequiredArgsConstructor
public class ViolationController {
    private final ViolationService violationService;
    private final ViolationMapper violationMapper;
    private final VehicleService vehicleService;
    private final FineService fineService;
    @GetMapping
    public String landingPage() {
        return "redirect:/inspect/violations";
    }
    @GetMapping("/violations")
    public String violationsPage(Model model) {
        model.addAttribute("violations", violationService.findAllViolationsInfo());
        return "pages/employee/all_violations";
    }
    @GetMapping("/violations/new")
    public String newViolationForm(ViolationDto violationDto, Model model) {
        model.addAttribute("fineList", fineService.findAllFinesInfo());
        return "pages/employee/new_violation_form";
    }
    @PostMapping("/violations/new")
    public String sendViolationForm(@Validated ViolationDto violationDto,
                                    BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("fineList", fineService.findAllFinesInfo());
            return "pages/employee/new_violation_form";
        }
        String registrationCode = violationDto.getRegistrationCode();
        VehiclesInfo vehicle = vehicleService.findVehicleInfoByRegistrationCode(registrationCode);
        if (vehicle == null) {
            String errorMessage = "Не найдено ТС с номером: " + registrationCode;
            ObjectError error = new ObjectError("violationDto",  errorMessage);
            result.addError(error);
            model.addAttribute("fineList", fineService.findAllFinesInfo());
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
        model.addAttribute("vehicleList", vehicleService.findAllVehiclesInfo());
        model.addAttribute("fineList", fineService.findAllFinesInfo());
        return "pages/employee/edit_violation_form";
    }
    @PostMapping("/violations/edit/{id}")
    public String sendViolationEditForm(@Validated ViolationDto violationDto, BindingResult result,
                                        @PathVariable Long id, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("vehicleList", vehicleService.findAllVehiclesInfo());
            model.addAttribute("fineList", fineService.findAllFinesInfo());
            return "pages/employee/edit_violation_form";
        }
        Violation violation = violationMapper.dtoToModel(violationDto);
        violation.setViolationId(id);
        violationService.save(violation);
        return "redirect:/inspect/violations";
    }
    @PostMapping("/violations/{id}")
    public String deleteViolation(@PathVariable Long id) {
        if (violationService.findById(id) != null) {
            violationService.deleteById(id);
        }
        return "redirect:/inspect/violations";
    }
}
