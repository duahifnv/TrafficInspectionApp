package dstu.inspection.controller.guest;

import com.itextpdf.text.DocumentException;
import dstu.inspection.entity.info.*;
import dstu.inspection.service.InfoService;
import dstu.inspection.utils.PdfBuilder;
import jakarta.annotation.security.PermitAll;
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
    private final InfoService infoService;
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
        if (!List.of("me", "inspect").contains(accessMode)) {
            return ResponseEntity.badRequest().build();
        }
        switch (resource) {
            case "categories" -> putAllCategories();
            case "fines" -> putAllFines();
            case "violations" -> putAllViolations(principal, accessMode);
            case "vehicles" -> putAllVehicles(principal, accessMode);
            case "licenses", "license" -> putAllLicenses(principal, accessMode);
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
        List<CategoriesInfo> categoriesInfo = infoService.findAllCategories();
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
    public void putAllFines()
            throws IOException, DocumentException {
        File file = pdfResource.getFile();
        String[] headers = new String[]{"Штрафы ГИБДД"};
        String[] tableHeaders = new String[]{"Описание штрафа", "Сумма штрафа"};
        List<FinesInfo> finesInfo = infoService.findAllFines();
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
        if (accessMode.equals("me")) {
            headers = new String[]{"Мои нарушения", getFullName(principal)};
            tableHeaders = new String[]{"Дата нарушения", "Дата погашения",
                    "Описание штрафа", "Сумма штрафа"};
            violationsInfo = infoService.findViolationsByUsername(principal.getName());
        }
        else if (accessMode.equals("inspect")) {
            headers = new String[]{"База нарушений", getFullName(principal)};
            tableHeaders = new String[]{"Регистрационный номер ТС",
                    "Дата нарушения", "Дата погашения", "Описание штрафа", "Сумма штрафа"};
            violationsInfo = infoService.findAllViolations();
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
        if (accessMode.equals("me")) {
            headers = new String[]{"Мои ТС", getFullName(principal)};
            tableHeaders = new String[]{"Дата регистрации ТС", "Регистрационный номер ТС",
                    "VIN-номер ТС"};
            vehiclesInfo = infoService.findVehicleInfoByUsername(principal.getName());
        }
        else if (accessMode.equals("inspect")) {
            headers = new String[]{"Транспортные средства", getFullName(principal)};
            tableHeaders = new String[]{"Дата регистрации ТС",
                    "Регистрационный номер ТС", "ФИО водителя", "VIN-номер ТС"};
            vehiclesInfo = infoService.findAllVehicles();
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
    public void putAllLicenses(Principal principal, String accessMode)
            throws IOException, DocumentException {
        File file = pdfResource.getFile();
        String[] headers;
        List<LicensesInfo> licensesInfo;
        if (accessMode.equals("me")) {
            headers = new String[]{"Мое удостоверение", getFullName(principal)};
            licensesInfo = List.of(infoService.findLicenseInfoByUsername(principal.getName()));
        }
        else if (accessMode.equals("inspect")) {
            headers = new String[]{"Водительские удостоверения", getFullName(principal)};
            licensesInfo = infoService.findAllLicenseInfos();
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
    private String getFullName(Principal principal) {
        String username = principal.getName();
        LicensesInfo license = infoService.findLicenseInfoByUsername(username);
        return license.getFullName();
    }
    private String convertToString(Date date) {
        if (date == null) return "-";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return simpleDateFormat.format(date);
    }
}
