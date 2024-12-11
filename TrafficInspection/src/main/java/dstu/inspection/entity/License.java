package dstu.inspection.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="driver_license")
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "license_id")
    private Long licenseId;
    @Column(name = "driver_id")
    private Long driverId;
    @Column(name = "department_id")
    private Long departmentId;
    @Column(name = "date_of_issue")
    private Date dateOfIssue;
    @Column(name = "date_of_expiry")
    private Date dateOfExpiry;
}
