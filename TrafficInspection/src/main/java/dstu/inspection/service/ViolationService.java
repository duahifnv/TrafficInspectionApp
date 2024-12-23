package dstu.inspection.service;

import dstu.inspection.entity.Violation;
import dstu.inspection.entity.info.ViolationsInfo;
import dstu.inspection.repository.ViolationRepository;
import dstu.inspection.repository.info.ViolationsInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViolationService {
    private final ViolationRepository violationRepository;
    private final ViolationsInfoRepository violationsInfoRepository;
    public Violation findById(Long id) {
        return violationRepository.findById(id).orElse(null);
    }
    public Violation save(Violation violation) {
        return violationRepository.save(violation);
    }
    public void deleteById(Long id) {
        violationRepository.deleteById(id);
    }
    public List<ViolationsInfo> findAllViolationsInfo() {
        return violationsInfoRepository.findAll(Sort.by(Sort.Direction.ASC, "violationId"));
    }
    public List<ViolationsInfo> findViolationsInfoByRegistrationCode(String registrationCode) {
        return violationsInfoRepository.findViolationsByRegistrationCode(registrationCode);
    }
    public List<ViolationsInfo> findViolationsInfoByUsername(String username) {
        return violationsInfoRepository.findViolationsByUsername(username);
    }
}
