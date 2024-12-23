package dstu.inspection.controller.inspect;

import dstu.inspection.dto.inspect.CertificateDto;
import dstu.inspection.entity.Certificate;
import dstu.inspection.entity.info.ViolationsInfo;
import dstu.inspection.mapper.CertificateMapper;
import dstu.inspection.service.*;
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

import java.util.List;


@Controller
@RequestMapping("/inspect/certificates")
@RequiredArgsConstructor
public class CertificateController {
    private final InfoService infoService;
    private final CategoryService categoryService;
    private final CertificateService certificateService;
    private final DriverService driverService;
    private final CertificateMapper certificateMapper;
    private final ViolationService violationService;
    @GetMapping
    public String certificatesPage(Model model) {
        model.addAttribute("certificates", infoService.findAllCertificates());
        return "pages/employee/all_certificates";
    }
    @GetMapping("/new")
    public String newCertificateForm(CertificateDto certificateDto, Model model) {
        addStaticAttributes(model);
        return "pages/employee/new_certificate_form";
    }
    @PostMapping("/new")
    public String sendCertificateForm(@Validated CertificateDto certificateDto,
                                      BindingResult result,
                                      Model model) {
        if (result.hasErrors()) {
            addStaticAttributes(model);
            return "pages/employee/new_certificate_form";
        }
        if (certificateExists(certificateDto)) {
            String errorMessage = "Уже зарегистрировано ТС с такими данными";
            ObjectError error = new ObjectError("certificateDto", errorMessage);
            result.addError(error);
            addStaticAttributes(model);
            return "pages/employee/new_certificate_form";
        }
        Certificate certificate = certificateMapper.dtoToModel(certificateDto);
        certificateService.save(certificate);
        return "redirect:/inspect/certificates";
    }
    @GetMapping("/edit/{id}")
    public String certificateEditForm(@PathVariable Long id, Model model) {
        Certificate certificate = certificateService.findById(id);
        if (certificate == null) {
            return "redirect:/inspect/certificates";
        }
        CertificateDto certificateDto = certificateMapper.modelToDto(certificate);
        model.addAttribute("certificateDto", certificateDto);
        addStaticAttributes(model);
        return "pages/employee/edit_certificate_form";
    }
    @PostMapping("/edit/{id}")
    public String sendCertificateEditForm(@Validated CertificateDto certificateDto,
                                          BindingResult result, @PathVariable Long id,
                                          Model model) {
        if (result.hasErrors()) {
            addStaticAttributes(model);
            return "pages/employee/edit_certificate_form";
        }
        Certificate certificate = certificateMapper.dtoToModel(certificateDto);
        certificateService.save(certificate);
        return "redirect:/inspect/certificates";
    }
    @PostMapping("/{id}")
    public String deleteCertificate(@PathVariable Long id) {
        Certificate certificate = certificateService.findById(id);
        if (certificate == null) {
            return "redirect:/inspect/certificates";
        }
        List<ViolationsInfo> violations = infoService.findViolationsByRegistrationCode(certificate.getRegistrationCode());
        certificateService.deleteById(id);
        return "redirect:/inspect/certificates";
    }
    private boolean certificateExists(CertificateDto certificateDto) {
        return certificateService.findByRegistrationCode(
                certificateDto.getRegistrationCode()
        ) != null
        &&
        certificateService.findByPassportId(
                certificateDto.getPassportId()
        ) != null;
    }
    private void addStaticAttributes(Model model) {
        model.addAttribute("driverList", driverService.findAll());
        model.addAttribute("categoryList", categoryService.findAll());
        model.addAttribute("departmentList", infoService.findAllDepartments());
    }
}
