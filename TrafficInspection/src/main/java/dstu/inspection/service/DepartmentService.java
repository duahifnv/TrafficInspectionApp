package dstu.inspection.service;

import dstu.inspection.entity.Department;
import dstu.inspection.repository.info.DepartmentInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentInfoRepository departmentInfoRepository;
    public List<Department> findAll() {
        return departmentInfoRepository.findAll();
    }
    public Optional<Department> findById(Long id) {
        return departmentInfoRepository.findById(id);
    }
}
