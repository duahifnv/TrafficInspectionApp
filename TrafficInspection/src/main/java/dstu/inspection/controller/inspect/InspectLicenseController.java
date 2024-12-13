package dstu.inspection.controller.inspect;

import dstu.inspection.dto.InspectLicenseDto;
import dstu.inspection.dto.UserLicenseDto;
import dstu.inspection.dto.ViolationDto;
import dstu.inspection.entity.License;
import dstu.inspection.entity.Person;
import dstu.inspection.service.InfoService;
import dstu.inspection.service.LicenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/inspect")
@RequiredArgsConstructor
public class InspectLicenseController {
    private final LicenseService licenseService;
    private final InfoService infoService;
    @GetMapping("/licenses")
    public String licensesPage(Model model) {
        model.addAttribute("licenses", infoService.findAllLicenseInfos());
        return "pages/employee/all_licenses";
    }
    @GetMapping("/licenses/new")
    public String newLicenseForm(Model model) {
        model.addAttribute("license", new InspectLicenseDto());
        return "pages/employee/new_license_form";
    }
    @PostMapping("/licenses/new")
    public String sendLicenseForm(@ModelAttribute("license") @Valid InspectLicenseDto licenseDto,
                                  BindingResult result, Model model) throws ParseException {
        if (result.hasErrors()) {
            return "pages/employee/new_license_form";
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfIssue = dateFormat.parse(licenseDto.getDateOfIssue());
        Date dateOfExpiry = dateFormat.parse(licenseDto.getDateOfExpiry());
        if (dateOfIssue.after(dateOfExpiry)) {
            String errorMessage = "Дата регистрации не может быть позже даты истечения";
            FieldError error = new FieldError("license",
                    "dateOfIssue", errorMessage);
            result.addError(error);
            model.addAttribute("license", new InspectLicenseDto());
            return "pages/employee/new_license_form";
        }
        License license = License.builder()
                .driverId(licenseDto.getDriverId())
                .departmentId(licenseDto.getDepartmentId())
                .dateOfIssue(dateOfIssue)
                .dateOfExpiry(dateOfExpiry)
                .build();
        licenseService.save(license);
        return "redirect:/inspect/licenses";
    }
}
