package dstu.inspection.repository.info;

import dstu.inspection.entity.info.CategoriesInfo;
import dstu.inspection.entity.info.CertificateInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificatesInfoRepository extends JpaRepository<CertificateInfo, Long> {
    @Query("select info from CertificateInfo info " +
            "inner join Certificate c on info.certificateId = c.certificateId " +
            "inner join License l on c.driverId = l.driverId " +
            "inner join User u on l.licenseId = u.licenseId " +
            "where u.username=?1")
    List<CertificateInfo> findAllCertificatesInfoByUsername(String username);
    CertificateInfo findCertificateInfoByRegistrationCode(String registrationCode);
}
