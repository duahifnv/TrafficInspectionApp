package dstu.trafficinspection.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="driver")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="driver_id")
    private Long driverId;
    @Column(name="full_name")
    private String fullName;
    @Column(name="date_of_birth")
    private Date dateOfBirth;
    @Column(name="registration_address")
    private String registrationAddress;
}
