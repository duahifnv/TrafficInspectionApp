package dstu.inspection.controller.guest;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dstu.inspection.entity.info.CategoriesInfo;
import dstu.inspection.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
