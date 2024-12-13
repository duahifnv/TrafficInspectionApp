package dstu.inspection.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "employee")
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "employee_id")
    private Long employeeId;
    @Basic
    @Column(name = "full_name")
    private String fullName;
    @Basic
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Basic
    @Column(name = "date_of_hire")
    @Temporal(TemporalType.DATE)
    private Date dateOfHire;
    @Basic
    @Column(name = "job_title")
    private String jobTitle;
    @Basic
    @Column(name = "department_id")
    private Long departmentId;
    @Basic
    @Column(name = "access_key")
    private String accessKey;
}
