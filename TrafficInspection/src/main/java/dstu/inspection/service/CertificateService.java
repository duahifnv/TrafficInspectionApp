package dstu.inspection.service;

import dstu.inspection.entity.Category;
import dstu.inspection.entity.Certificate;
import dstu.inspection.entity.info.CertificateInfo;
import dstu.inspection.repository.CategoryRepository;
import dstu.inspection.repository.CertificateRepository;
import dstu.inspection.repository.info.CertificatesInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.core.support.DefaultCrudMethods;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateService {
    private final CertificateRepository certificateRepository;
    private final CertificatesInfoRepository certificatesInfoRepository;
    public List<Certificate> findAll() {
        return certificateRepository.findAll();
    }
    public Certificate findById(Long id) {
        return certificateRepository.findById(id).orElse(null);
    }
    public Certificate findByRegistrationCode(String registrationCode) {
        return certificateRepository.findCertificateByRegistrationCode(registrationCode);
    }
    public Certificate findByPassportId(Long passportId) {
        return certificateRepository.findCertificateByPassportId(passportId);
    }
    public Certificate save(Certificate certificate) {
        return certificateRepository.save(certificate);
    }
    public void deleteById(Long id) {
        certificateRepository.deleteById(id);
    }
    public List<CertificateInfo> findAllCertificatesInfo() {
        return certificatesInfoRepository.findAll(Sort.by(Sort.Direction.ASC, "certificateId"));
    }
    public CertificateInfo findCertificateInfoByRegistrationCode(String registrationCode) {
        return certificatesInfoRepository.findCertificateInfoByRegistrationCode(registrationCode);
    }
    public List<CertificateInfo> findAllCertificatesInfoByUsername(String username) {
        return certificatesInfoRepository.findAllCertificatesInfoByUsername(username);
    }
}
