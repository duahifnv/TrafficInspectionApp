package dstu.inspection.entity.info;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "traffic_fine")
@Immutable
public class FinesInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fine_id")
    private Long fineId;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "fine_amount", nullable = false)
    private BigDecimal fineAmount;
}
