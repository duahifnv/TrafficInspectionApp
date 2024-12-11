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
@Table(name = "vehicle_registration_certificate")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificate_id")
    private Long certificateId;
    @Column(name = "registration_code")
    private String registrationCode;
    @Column(name = "passport_id")
    private Long passportId;
    @Column(name = "driver_id")
    private Long driverId;
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "department_id")
    private Long departmentId;
    @Column(name = "date_of_registration")
    @Temporal(TemporalType.DATE)
    private Date dateOfRegistration;
}
