package dstu.inspection.repository.info;

import dstu.inspection.entity.info.CategoriesInfo;
import dstu.inspection.entity.info.ViolationsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViolationsInfoRepository extends JpaRepository<ViolationsInfo, Long> {
    List<ViolationsInfo> findByRegistrationCode(String registrationCode);
}
