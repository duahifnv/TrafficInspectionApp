package dstu.inspection.service;

import dstu.inspection.entity.License;
import dstu.inspection.repository.LicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LicenseService {
    private final LicenseRepository licenseRepository;
    public License findById(Long id) {
        return licenseRepository.findById(id).orElse(null);
    }
    public License findByDriverId(Long id) {
        return licenseRepository.findByDriverId(id);
    }
    public License save(License license) {
        return licenseRepository.save(license);
    }
    public void deleteById(Long id) {
        licenseRepository.deleteById(id);
    }
}
