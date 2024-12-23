package dstu.inspection.controller.inspect;

import dstu.inspection.dto.inspect.LicenseDto;
import dstu.inspection.entity.License;
import dstu.inspection.mapper.LicenseMapper;
import dstu.inspection.service.DepartmentService;
import dstu.inspection.service.DriverService;
import dstu.inspection.service.VehicleService;
import dstu.inspection.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/inspect")
@RequiredArgsConstructor
public class LicenseController {
    private final LicenseService licenseService;
    private final DepartmentService departmentService;
    private final DriverService driverService;
    private final LicenseMapper licenseMapper;
    @GetMapping("/licenses")
    public String licensesPage(Model model) {
        model.addAttribute("licenses", licenseService.findAllLicenseInfos());
        return "pages/employee/all_licenses";
    }
    @GetMapping("/licenses/new")
    public String newLicenseForm(LicenseDto licenseDto, Model model) {
        addStaticAttributes(model);
        return "pages/employee/new_license_form";
    }
    @PostMapping("/licenses/new")
    public String putLicenseForm(@Validated LicenseDto licenseDto,
                                  BindingResult result, Model model) throws ParseException {
        if (result.hasErrors()) {
            addStaticAttributes(model);
            return "pages/employee/new_license_form";
        }
        License licenseByDriverId = licenseService.findByDriverId(licenseDto.getDriverId());
        if (licenseByDriverId != null) {
            String errorMessage = "Этому водителю уже выдано удостоверение";
            ObjectError error = new ObjectError("licenseDto", errorMessage);
            result.addError(error);
            addStaticAttributes(model);
            return "pages/employee/new_license_form";
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfIssue = dateFormat.parse(licenseDto.getDateOfIssue());
        Date dateOfExpiry = dateFormat.parse(licenseDto.getDateOfExpiry());
        License license = License.builder()
                .driverId(licenseDto.getDriverId())
                .departmentId(licenseDto.getDepartmentId())
                .dateOfIssue(dateOfIssue)
                .dateOfExpiry(dateOfExpiry)
                .build();
        licenseService.save(license);
        return "redirect:/inspect/licenses";
    }
    @GetMapping("/licenses/edit/{id}")
    public String licenseEditForm(@PathVariable Long id, Model model) {
        License license = licenseService.findById(id);
        if (license == null) {
            return "redirect:/inspect/licenses";
        }
        LicenseDto licenseDto = licenseMapper.modelToDto(license);
        addStaticAttributes(model);
        model.addAttribute("licenseDto", licenseDto);
        return "pages/employee/edit_license_form";
    }
    @PostMapping("/licenses/edit/{id}")
    public String sendViolationEditForm(@Validated LicenseDto licenseDto, BindingResult result,
                                        @PathVariable Long id, Model model) {
        if (result.hasErrors()) {
            addStaticAttributes(model);
            return "pages/employee/edit_license_form";
        }
        License license = licenseMapper.dtoToModel(licenseDto);
        licenseService.save(license);
        return "redirect:/inspect/licenses";
    }
    @PostMapping("/licenses/{id}")
    public String deleteViolation(@PathVariable Long id) {
        if (licenseService.findById(id) != null) {
            licenseService.deleteById(id);
        }
        return "redirect:/inspect/licenses";
    }
    private void addStaticAttributes(Model model) {
        model.addAttribute("driverList", driverService.findAll());
        model.addAttribute("departmentList", departmentService.findAll());
    }
}
