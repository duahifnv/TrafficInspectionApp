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
@Table(name="traffic_violations")
public class Violation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "violation_id")
    private Long violationId;
    @Column(name = "registration_code")
    private String registrationCode;
    @Column(name = "fine_id")
    private Long fineId;
    @Column(name = "date_of_violation")
    @Temporal(TemporalType.DATE)
    private Date dateOfViolation;
    @Column(name = "date_of_payment")
    @Temporal(TemporalType.DATE)
    private Date dateOfPayment;
}
