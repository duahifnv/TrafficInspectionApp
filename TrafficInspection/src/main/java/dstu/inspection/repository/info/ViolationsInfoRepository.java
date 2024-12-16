package dstu.inspection.repository.info;

import dstu.inspection.entity.info.CategoriesInfo;
import dstu.inspection.entity.info.ViolationsInfo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViolationsInfoRepository extends JpaRepository<ViolationsInfo, Long> {
    @Override
    List<ViolationsInfo> findAll(Sort sort);
    List<ViolationsInfo> findViolationsByRegistrationCode(String registrationCode);
    @Query("select info from ViolationsInfo info " +
            "inner join UsersLicensesInfo u on info.registrationCode = u.registrationCode " +
            "where u.username=?1")
    List<ViolationsInfo> findViolationsByUsername(String username);
}
