package dstu.inspection.repository;

import dstu.inspection.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    /**
     * Поиск сотрудника по его имени в системе
     * @param username имя под которым сотрудник зарегистрирован в системе
     * @return объект сотрудника
     */
    @Query("select emp from Employee emp inner join User as u " +
            "on u.password = emp.accessKey where u.username = ?1")
    Employee findByUsername(String username);
}
