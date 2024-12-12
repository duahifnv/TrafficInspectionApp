package dstu.inspection.service;

import dstu.inspection.entity.Violation;
import dstu.inspection.repository.ViolationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViolationService {
    private final ViolationRepository violationRepository;
    public Violation findById(Long id) {
        return violationRepository.findById(id).orElse(null);
    }
    public Violation save(Violation violation) {
        return violationRepository.save(violation);
    }
}
