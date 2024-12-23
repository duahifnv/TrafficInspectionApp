package dstu.inspection.service;

import dstu.inspection.entity.License;
import dstu.inspection.entity.info.LicensesInfo;
import dstu.inspection.repository.LicenseRepository;
import dstu.inspection.repository.info.LicensesInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LicenseService {
    private final LicenseRepository licenseRepository;
    private final LicensesInfoRepository licensesInfoRepository;

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
    public List<LicensesInfo> findAllLicenseInfos() {
        return licensesInfoRepository.findAll(Sort.by(Sort.Direction.ASC, "fullName"));
    }
    public LicensesInfo findLicenseInfoById(Long id) {
        return licensesInfoRepository.findById(id).orElse(null);
    }
    public LicensesInfo findLicenseInfoByUsername(String username) {
        return licensesInfoRepository.findByUsername(username);
    }
}
