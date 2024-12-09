package dstu.inspection.entity.info;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "violations_info")
@Immutable
public class ViolationsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "violation_id")
    private Long violationId;
    @Column(name = "registration_code")
    private String registrationCode;
    private String description;
    @Column(name = "fine_amount")
    private BigDecimal fineAmount;
    @Column(name = "date_of_violation")
    @Temporal(TemporalType.DATE)
    private Date dateOfViolation;
    @Column(name = "date_of_payment")
    @Temporal(TemporalType.DATE)
    private Date dateOfPayment;
}
