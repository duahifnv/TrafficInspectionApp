package dstu.inspection.service;

import dstu.inspection.entity.Employee;
import dstu.inspection.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }
}
