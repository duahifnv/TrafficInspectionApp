package dstu.inspection.entity.info;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "licenses_info")
@Immutable
public class LicensesInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "license_id")
    private Long licenseId;
    @Column(name = "full_name")
    private String fullName;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "registration_address")
    private String registrationAddress;
    @Column(name = "department_type")
    private String departmentType;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_issue")
    private Date dateOfIssue;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_expiry")
    private Date dateOfExpiry;
}
