package dstu.inspection.entity.info;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Immutable
@Entity
@Table(name = "certificate_info")
public class CertificateInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificate_id")
    private Long certificateId;
    @Column(name = "registration_code")
    private String registrationCode;
    @Column(name = "date_of_registration")
    private Date dateOfRegistration;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "vin")
    private String vin;
    @Column(name = "category_code")
    private String categoryCode;
    @Column(name = "department_type")
    private String departmentType;
    @Column(name = "department_location")
    private String departmentLocation;
}
