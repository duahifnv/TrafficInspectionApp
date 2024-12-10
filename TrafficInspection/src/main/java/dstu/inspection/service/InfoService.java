package dstu.inspection.service;

import dstu.inspection.entity.Department;
import dstu.inspection.entity.info.CategoriesInfo;
import dstu.inspection.entity.info.EmployeesInfo;
import dstu.inspection.entity.info.FinesInfo;
import dstu.inspection.entity.info.ViolationsInfo;
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
    public List<ViolationsInfo> findByRegistrationCode(String registrationCode) {
        return violationsInfoRepository.findByRegistrationCode(registrationCode);
    }
}
