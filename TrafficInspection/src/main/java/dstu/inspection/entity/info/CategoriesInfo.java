package dstu.inspection.entity.info;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "categories_info")
@Immutable
public class CategoriesInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_code")
    private String categoryCode;
    @Column(name = "permitted_vehicle_name")
    private String vehicleName;
    @Column(name = "minimal_age")
    private Integer minimalAge;
}
