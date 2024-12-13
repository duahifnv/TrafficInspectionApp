package dstu.inspection.entity.info;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users_licenses_info")
@Immutable
public class UsersLicensesInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_code")
    private String registrationCode;
    private String username;
}
