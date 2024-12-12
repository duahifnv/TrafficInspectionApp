package dstu.inspection.service;

import dstu.inspection.entity.Department;
import dstu.inspection.entity.License;
import dstu.inspection.entity.info.*;
import dstu.inspection.repository.LicenseRepository;
import dstu.inspection.repository.info.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InfoService {
    private final CategoriesInfoRepository categoriesInfoRepository;
    private final FinesInfoRepository finesInfoRepository;
    private final EmployeesInfoRepository employeesInfoRepository;
    private final DepartmentInfoRepository departmentInfoRepository;
    private final ViolationsInfoRepository violationsInfoRepository;
    private final LicensesInfoRepository licensesInfoRepository;
    private final VehiclesInfoRepository vehiclesInfoRepository;
    public List<CategoriesInfo> findAllCategories() {
        return categoriesInfoRepository.findAll();
    }
    public List<FinesInfo> findAllFines() {
        return finesInfoRepository.findAll();
    }
    public List<EmployeesInfo> findAllEmployees() {
        return employeesInfoRepository.findAll();
    }
    public List<Department> findAllDepartments() {
        return departmentInfoRepository.findAll();
    }
    public List<ViolationsInfo> findAllViolations() {
        return violationsInfoRepository.findAll();
    }
    public List<ViolationsInfo> findViolationsByRegistrationCode(String registrationCode) {
        return violationsInfoRepository.findViolationsByRegistrationCode(registrationCode);
    }
    public List<ViolationsInfo> findViolationsByPhone(String phone) {
        return violationsInfoRepository.findViolationsByPhone(phone);
    }
    public List<LicensesInfo> findAllLicenseInfos() {
        return licensesInfoRepository.findAll();
    }
    public LicensesInfo findLicenseInfoById(Long id) {
        return licensesInfoRepository.findById(id).orElse(null);
    }
    public LicensesInfo findLicenseInfoByPhone(String phone) {
        return licensesInfoRepository.findByPhone(phone);
    }
    public List<VehiclesInfo> findVehicleInfoByPhone(String phone) {
        return vehiclesInfoRepository.findByPhone(phone);
    }
}
