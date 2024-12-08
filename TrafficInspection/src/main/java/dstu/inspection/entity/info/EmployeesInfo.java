package dstu.inspection.entity.info;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "employees_info")
@Immutable
public class EmployeesInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "department_type")
    private String departmentType;
    @Column(name = "department_location")
    private String departmentLocation;
}
