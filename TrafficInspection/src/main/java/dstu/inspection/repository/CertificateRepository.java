package dstu.inspection.repository;

import dstu.inspection.entity.Certificate;
import dstu.inspection.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    Certificate findCertificateByRegistrationCode(String registrationCode);
    Certificate findCertificateByPassportId(Long passportId);
}
