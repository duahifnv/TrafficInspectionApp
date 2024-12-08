package dstu.inspection.service;

import dstu.inspection.entity.Category;
import dstu.inspection.entity.Fine;
import dstu.inspection.repository.CategoryRepository;
import dstu.inspection.repository.FineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaticService {
    private final CategoryRepository categoryRepository;
    private final FineRepository fineRepository;
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
    public List<Fine> findAllFines() {
        return fineRepository.findAll();
    }
    // ...
}
