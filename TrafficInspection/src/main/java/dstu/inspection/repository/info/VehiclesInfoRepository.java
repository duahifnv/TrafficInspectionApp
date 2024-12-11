package dstu.inspection.repository.info;

import dstu.inspection.entity.info.VehiclesInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiclesInfoRepository extends JpaRepository<VehiclesInfo, Long> {
    @Query("select info from VehiclesInfo info " +
            "inner join Certificate c on info.registrationCode = c.registrationCode" +
            " inner join License l on c.driverId = l.driverId" +
            " inner join User u on l.licenseId = u.licenseId" +
            " where u.phone = ?1")
    List<VehiclesInfo> findByPhone(String phone);
}
