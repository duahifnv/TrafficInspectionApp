package dstu.inspection.controller;

import com.itextpdf.text.DocumentException;
import dstu.inspection.entity.Department;
import dstu.inspection.entity.Employee;
import dstu.inspection.entity.info.*;
import dstu.inspection.service.*;
import dstu.inspection.utils.PdfBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PdfController {
    private static final String EMPLOYEE_MODE_NAME = "inspect";
    private static final String USER_MODE_NAME = "me";
    private final FineService fineService;
    private final CategoryService categoryService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final ViolationService violationService;
    private final VehicleService vehicleService;
    private final LicenseService licenseService;
    private final CertificateService certificateService;
    @Value("classpath:static/pdf/pdf_cached.pdf")
    private Resource pdfResource;
    @Value("classpath:static/times_new_roman.ttf")
    private Resource resource;
    @GetMapping("/{resource}/pdf")
    public ResponseEntity<Resource> sendResourceToGuest(@PathVariable String resource)
            throws DocumentException, IOException {
        switch (resource) {
            case "categories" -> putAllCategories();
            case "fines" -> putAllFines();
            case "departments" -> putAllDepartments();
            case "employees" -> putAllEmployees();
            default -> {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfResource);
    }
    @GetMapping("/{accessMode}/{resource}/pdf")
    public ResponseEntity<Resource> sendResourceToAuthorized(@PathVariable String resource,
                                                          @PathVariable String accessMode,
                                                          Principal principal)
            throws IOException, DocumentException
    {
        if (!List.of(USER_MODE_NAME, EMPLOYEE_MODE_NAME).contains(accessMode)) {
            return ResponseEntity.badRequest().build();
        }
        switch (resource) {
            case "violations" -> putAllViolations(principal, accessMode);
            case "vehicles" -> putAllVehicles(principal, accessMode);
            case "licenses", "license" -> putAllLicenses(principal, accessMode);
            case "certificates" -> putAllCertificates(principal, accessMode);
            default -> {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfResource);
    }
    public void putAllCategories()
            throws IOException, DocumentException {
        File file = pdfResource.getFile();
        String[] headers = new String[]{"Категории ТС"};
        String[] tableHeaders = new String[]{"Обозначение категории",
                "Разрешенное ТС", "Минимальный возраст"};
        List<CategoriesInfo> categoriesInfo = categoryService.findAllCategoriesInfo();
        String[][] tableData = new String[categoriesInfo.size()][tableHeaders.length];
        for (int i = 0; i < categoriesInfo.size(); i++) {
            CategoriesInfo category = categoriesInfo.get(i);
            tableData[i][0] = category.getCategoryCode();
            tableData[i][1] = category.getVehicleName();
            tableData[i][2] = category.getMinimalAge().toString();
        }
        PdfBuilder pdfBuilder = new PdfBuilder();
        pdfBuilder.createTablePdf(headers, tableHeaders, tableData, file, resource);
    }
    public void putAllDepartments() throws IOException, DocumentException {
        File file = pdfResource.getFile();
        String[] headers = new String[]{"Подразделения ГИБДД"};
        String[] tableHeaders = new String[]{"Тип подразделения", "Адрес подразделения"};
        List<Department> departments = departmentService.findAll();
        String[][] tableData = new String[departments.size()][tableHeaders.length];
        for (int i = 0; i < departments.size(); i++) {
            Department department = departments.get(i);
            tableData[i][0]= department.getDepartmentType();
            tableData[i][1]= department.getDepartmentLocation();
        }
        PdfBuilder pdfBuilder = new PdfBuilder();
        pdfBuilder.createTablePdf(headers, tableHeaders, tableData, file, resource);
    }
    public void putAllEmployees() throws IOException, DocumentException {
        File file = pdfResource.getFile();
        String[] headers = new String[]{"Сотрудники ГИБДД"};
        String[] tableHeaders = new String[]{"ФИО", "Должность",
                "Тип подразделения", "Адрес подразделения"};
        List<EmployeesInfo> employees = employeeService.findAllEmployeesInfo();
        String[][] tableData = new String[employees.size()][tableHeaders.length];
        for (int i = 0; i < employees.size(); i++) {
            EmployeesInfo employee = employees.get(i);
            tableData[i][0]= employee.getFullName();
            tableData[i][1]= employee.getJobTitle();
            tableData[i][2]= employee.getDepartmentType();
            tableData[i][3]= employee.getDepartmentLocation();
        }
        PdfBuilder pdfBuilder = new PdfBuilder();
        pdfBuilder.createTablePdf(headers, tableHeaders, tableData, file, resource);
    }
    public void putAllFines()
            throws IOException, DocumentException {
        File file = pdfResource.getFile();
        String[] headers = new String[]{"Штрафы ГИБДД"};
        String[] tableHeaders = new String[]{"Описание штрафа", "Сумма штрафа"};
        List<FinesInfo> finesInfo = fineService.findAllFinesInfo();
        String[][] tableData = new String[finesInfo.size()][tableHeaders.length];
        for (int i = 0; i < finesInfo.size(); i++) {
            FinesInfo fine = finesInfo.get(i);
            tableData[i][0]= fine.getDescription();
            tableData[i][1]= fine.getFineAmount() + " руб.";
        }
        PdfBuilder pdfBuilder = new PdfBuilder();
        pdfBuilder.createTablePdf(headers, tableHeaders, tableData, file, resource);
    }
    public void putAllViolations(Principal principal, String accessMode)
            throws IOException, DocumentException {
        File file = pdfResource.getFile();
        String[] headers;
        String[] tableHeaders;
        List<ViolationsInfo> violationsInfo;
        if (accessMode.equals(USER_MODE_NAME)) {
            headers = new String[]{"Мои нарушения", getFullName(principal, accessMode)};
            tableHeaders = new String[]{"Дата нарушения", "Дата погашения",
                    "Описание штрафа", "Сумма штрафа"};
            violationsInfo = violationService.findViolationsInfoByUsername(principal.getName());
        }
        else if (accessMode.equals(EMPLOYEE_MODE_NAME)) {
            headers = new String[]{"База нарушений", getFullName(principal, accessMode)};
            tableHeaders = new String[]{"Регистрационный номер ТС",
                    "Дата нарушения", "Дата погашения", "Описание штрафа", "Сумма штрафа"};
            violationsInfo = violationService.findAllViolationsInfo();
        }
        else throw new DocumentException();
        String[][] tableData = new String[violationsInfo.size()][tableHeaders.length];
        for (int i = 0; i < violationsInfo.size(); i++) {
            ViolationsInfo violation = violationsInfo.get(i);
            if (tableHeaders.length == 4) {
                tableData[i][0] = convertToString(violation.getDateOfViolation());
                tableData[i][1] = convertToString(violation.getDateOfPayment());
                tableData[i][2] = violation.getDescription();
                tableData[i][3] = violation.getFineAmount() + " руб.";
            }
            if (tableHeaders.length == 5) {
                tableData[i][0] = violation.getRegistrationCode();
                tableData[i][1] = convertToString(violation.getDateOfViolation());
                tableData[i][2] = convertToString(violation.getDateOfPayment());
                tableData[i][3] = violation.getDescription();
                tableData[i][4] = violation.getFineAmount() + " руб.";
            }
        }
        PdfBuilder pdfBuilder = new PdfBuilder();
        pdfBuilder.createTablePdf(headers, tableHeaders, tableData, file, resource);
    }
    public void putAllVehicles(Principal principal, String accessMode) throws IOException, DocumentException {
        File file = pdfResource.getFile();
        String[] headers;
        String[] tableHeaders;
        List<VehiclesInfo> vehiclesInfo;
        if (accessMode.equals(USER_MODE_NAME)) {
            headers = new String[]{"Мои ТС", getFullName(principal, accessMode)};
            tableHeaders = new String[]{"Дата регистрации ТС", "Регистрационный номер ТС",
                    "VIN-номер ТС"};

            vehiclesInfo = vehicleService.findAllVehiclesInfoByUsername(principal.getName());
        }
        else if (accessMode.equals(EMPLOYEE_MODE_NAME)) {
            headers = new String[]{"Транспортные средства", getFullName(principal, accessMode)};
            tableHeaders = new String[]{"Дата регистрации ТС",
                    "Регистрационный номер ТС", "ФИО водителя", "VIN-номер ТС"};
            vehiclesInfo = vehicleService.findAllVehiclesInfo();
        }
        else throw new DocumentException();
        String[][] tableData = new String[vehiclesInfo.size()][tableHeaders.length];
        for (int i = 0; i < vehiclesInfo.size(); i++) {
            VehiclesInfo vehicle = vehiclesInfo.get(i);
            tableData[i][0] = convertToString(vehicle.getDateOfRegistration());
            tableData[i][1] = vehicle.getRegistrationCode();
            if (tableHeaders.length == 3) {
                tableData[i][2] = vehicle.getVin();
            }
            if (tableHeaders.length == 4) {
                tableData[i][2] = vehicle.getFullName();
                tableData[i][3] = vehicle.getVin();
            }
        }
        PdfBuilder pdfBuilder = new PdfBuilder();
        pdfBuilder.createTablePdf(headers, tableHeaders, tableData, file, resource);
    }
    public void putAllCertificates(Principal principal, String accessMode)
            throws IOException, DocumentException {
        File file = pdfResource.getFile();
        String[] headers;
        List<CertificateInfo> certificateInfos;
        if (accessMode.equals(USER_MODE_NAME)) {
            headers = new String[]{"Мои свидетельства о регистрации ТС", getFullName(principal, accessMode)};
            certificateInfos = certificateService.findAllCertificatesInfoByUsername(principal.getName());
        }
        else if (accessMode.equals(EMPLOYEE_MODE_NAME)) {
            headers = new String[]{"Свидетельства о регистрации ТС", getFullName(principal, accessMode)};
            certificateInfos = certificateService.findAllCertificatesInfo();
        }
        else throw new DocumentException();
        String[] tableHeaders = new String[]{"Рег. номер", "Дата рег.", "ФИО",
                "VIN-номер", "Кат.", "Тип подразделения", "Адрес подразделения"};
        String[][] tableData = new String[certificateInfos.size()][tableHeaders.length];
        for (int i = 0; i < certificateInfos.size(); i++) {
            CertificateInfo certificateInfo = certificateInfos.get(i);
            tableData[i][0] = certificateInfo.getRegistrationCode();
            tableData[i][1] = convertToString(certificateInfo.getDateOfRegistration());
            tableData[i][2] = certificateInfo.getFullName();
            tableData[i][3] = certificateInfo.getVin();
            tableData[i][4] = certificateInfo.getCategoryCode();
            tableData[i][5] = certificateInfo.getDepartmentType();
            tableData[i][6] = certificateInfo.getDepartmentLocation();
        }
        PdfBuilder pdfBuilder = new PdfBuilder();
        pdfBuilder.createTablePdf(headers, tableHeaders, tableData, file, resource);
    }
    public void putAllLicenses(Principal principal, String accessMode)
            throws IOException, DocumentException {
        File file = pdfResource.getFile();
        String[] headers;
        List<LicensesInfo> licensesInfo;
        if (accessMode.equals(USER_MODE_NAME)) {
            headers = new String[]{"Мое удостоверение", getFullName(principal, accessMode)};
            licensesInfo = List.of(licenseService.findLicenseInfoByUsername(principal.getName()));
        }
        else if (accessMode.equals(EMPLOYEE_MODE_NAME)) {
            headers = new String[]{"Водительские удостоверения", getFullName(principal, accessMode)};
            licensesInfo = licenseService.findAllLicenseInfos();
        }
        else throw new DocumentException();
        String[] tableHeaders = new String[]{"ФИО", "Дата рождения", "Место прописки",
                "Тип подразделения", "Дата выдачи", "Дата окончания действия"};
        String[][] tableData = new String[licensesInfo.size()][tableHeaders.length];
        for (int i = 0; i < licensesInfo.size(); i++) {
            LicensesInfo license = licensesInfo.get(i);
            tableData[i][0]= license.getFullName();
            tableData[i][1]= convertToString(license.getDateOfBirth());
            tableData[i][2]= license.getRegistrationAddress();
            tableData[i][3]= license.getDepartmentType();
            tableData[i][4]= convertToString(license.getDateOfIssue());
            tableData[i][5]= convertToString(license.getDateOfExpiry());
        }
        PdfBuilder pdfBuilder = new PdfBuilder();
        pdfBuilder.createTablePdf(headers, tableHeaders, tableData, file, resource);
    }
    private String getFullName(Principal principal, String accessMode) {
        String username = principal.getName();
        if (accessMode.equals(EMPLOYEE_MODE_NAME)) {
            Employee employee = employeeService.findByUsername(username);
            return employee.getFullName();
        }
        else {
            LicensesInfo license = licenseService.findLicenseInfoByUsername(username);
            return license.getFullName();
        }
    }
    private String convertToString(Date date) {
        if (date == null) return "-";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return simpleDateFormat.format(date);
    }
}
