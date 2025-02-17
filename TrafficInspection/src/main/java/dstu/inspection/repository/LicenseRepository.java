package dstu.inspection.repository;

import dstu.inspection.dto.inspect.LicenseDto;
import dstu.inspection.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends JpaRepository<License, Long> {
    License findByDriverId(Long id);
}
