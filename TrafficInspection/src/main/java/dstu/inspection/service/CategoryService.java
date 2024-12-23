package dstu.inspection.service;

import dstu.inspection.entity.Category;
import dstu.inspection.entity.Employee;
import dstu.inspection.entity.info.CategoriesInfo;
import dstu.inspection.repository.CategoryRepository;
import dstu.inspection.repository.EmployeeRepository;
import dstu.inspection.repository.info.CategoriesInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoriesInfoRepository categoriesInfoRepository;
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    public List<CategoriesInfo> findAllCategoriesInfo() {
        return categoriesInfoRepository.findAll();
    }
}
