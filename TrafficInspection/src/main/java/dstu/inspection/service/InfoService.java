package dstu.inspection.service;

import dstu.inspection.entity.Department;
import dstu.inspection.entity.License;
import dstu.inspection.entity.info.*;
import dstu.inspection.repository.LicenseRepository;
import dstu.inspection.repository.info.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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
        return violationsInfoRepository.findAll(Sort.by(Sort.Direction.ASC, "violationId"));
    }
    public List<ViolationsInfo> findViolationsByRegistrationCode(String registrationCode) {
        return violationsInfoRepository.findViolationsByRegistrationCode(registrationCode);
    }
    public List<ViolationsInfo> findViolationsByUsername(String username) {
        return violationsInfoRepository.findViolationsByUsername(username);
    }
    public List<LicensesInfo> findAllLicenseInfos() {
        return licensesInfoRepository.findAll();
    }
    public LicensesInfo findLicenseInfoById(Long id) {
        return licensesInfoRepository.findById(id).orElse(null);
    }
    public LicensesInfo findLicenseInfoByUsername(String username) {
        return licensesInfoRepository.findByUsername(username);
    }
    public List<VehiclesInfo> findAllVehicles() {
        return vehiclesInfoRepository.findAll();
    }
    public VehiclesInfo findVehicleInfoByRegistrationCode(String registrationCode) {
        return vehiclesInfoRepository.findByRegistrationCode(registrationCode);
    }
    public List<VehiclesInfo> findVehicleInfoByUsername(String username) {
        return vehiclesInfoRepository.findByUsername(username);
    }
}
