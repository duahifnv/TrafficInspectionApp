package dstu.inspection.repository.info;

import dstu.inspection.entity.info.CategoriesInfo;
import dstu.inspection.entity.info.ViolationsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViolationsInfoRepository extends JpaRepository<ViolationsInfo, Long> {
    List<ViolationsInfo> findViolationsByRegistrationCode(String registrationCode);
    @Query("select info from ViolationsInfo info " +
            "inner join UsersLicensesInfo u on info.registrationCode = u.registrationCode " +
            "where u.phone=?1")
    List<ViolationsInfo> findViolationsByPhone(String phone);
}
