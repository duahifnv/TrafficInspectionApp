package dstu.inspection.repository.info;

import dstu.inspection.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentInfoRepository extends JpaRepository<Department, Long> {
}
