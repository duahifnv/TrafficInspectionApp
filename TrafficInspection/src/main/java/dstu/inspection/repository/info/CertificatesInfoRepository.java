package dstu.inspection.repository.info;

import dstu.inspection.entity.info.CategoriesInfo;
import dstu.inspection.entity.info.CertificateInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificatesInfoRepository extends JpaRepository<CertificateInfo, Long> {
    CertificateInfo findCertificateInfoByRegistrationCode(String registrationCode);
}
