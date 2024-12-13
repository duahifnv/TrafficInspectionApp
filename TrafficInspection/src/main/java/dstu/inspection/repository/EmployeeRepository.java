package dstu.inspection.repository;

import dstu.inspection.entity.Driver;
import dstu.inspection.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
