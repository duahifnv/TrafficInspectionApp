package dstu.inspection.service;

import dstu.inspection.entity.Employee;
import dstu.inspection.entity.info.EmployeesInfo;
import dstu.inspection.repository.EmployeeRepository;
import dstu.inspection.repository.info.EmployeesInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeesInfoRepository employeesInfoRepository;
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }
    public List<EmployeesInfo> findAllEmployeesInfo() {
        return employeesInfoRepository.findAll();
    }
}
