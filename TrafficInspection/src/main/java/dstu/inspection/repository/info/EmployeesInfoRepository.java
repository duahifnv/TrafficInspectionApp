package dstu.inspection.repository.info;

import dstu.inspection.entity.info.EmployeesInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesInfoRepository extends JpaRepository<EmployeesInfo, Long> {
}
