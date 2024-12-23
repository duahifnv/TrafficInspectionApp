package dstu.inspection.service;

import dstu.inspection.entity.Department;
import dstu.inspection.repository.info.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }
    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }
}
