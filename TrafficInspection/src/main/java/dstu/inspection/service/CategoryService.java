package dstu.inspection.service;

import dstu.inspection.entity.Category;
import dstu.inspection.entity.Employee;
import dstu.inspection.repository.CategoryRepository;
import dstu.inspection.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
