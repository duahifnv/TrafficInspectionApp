package dstu.inspection.entity.info;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "vehicles_info")
@Immutable
public class VehiclesInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_code")
    private String registrationCode;
    @Column(name = "vin")
    private String vin;
    @Column(name = "model_name")
    private String modelName;
    @Column(name = "brand_name")
    private String brandName;
    @Column(name = "manufacture_year")
    private Integer manufactureYear;
    @Column(name = "body_color")
    private String bodyColor;
    @Column(name = "category_code")
    private String categoryCode;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "date_of_registration")
    @Temporal(TemporalType.DATE)
    private Date dateOfRegistration;
}
