package dstu.inspection.repository.info;

import dstu.inspection.entity.info.VehiclesInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiclesInfoRepository extends JpaRepository<VehiclesInfo, Long> {
    VehiclesInfo findByRegistrationCode(String registrationCode);
    @Query("select info from VehiclesInfo info " +
            "inner join Certificate c on info.registrationCode = c.registrationCode" +
            " inner join License l on c.driverId = l.driverId" +
            " inner join User u on l.licenseId = u.licenseId" +
            " where u.username = ?1")
    List<VehiclesInfo> findByUsername(String username);
}
