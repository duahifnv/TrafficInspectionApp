package dstu.inspection.repository.info;

import dstu.inspection.entity.info.LicensesInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LicensesInfoRepository extends JpaRepository<LicensesInfo, Long> {
    @Query("select info from LicensesInfo info " +
            "inner join User u on info.licenseId = u.license_id where u.phone = ?1")
    LicensesInfo findByPhone(String phone);
}
