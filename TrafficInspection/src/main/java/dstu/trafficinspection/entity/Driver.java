package dstu.trafficinspection.entity;

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
@Table(name="driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="driver_id")
    private Long driverId;
    @Column(name="full_name", nullable = false)
    private String fullName;
    @Column(name="date_of_birth", nullable = false)
    private Date dateOfBirth;
    @Column(name="registration_address", nullable = false)
    private String registrationAddress;
}
