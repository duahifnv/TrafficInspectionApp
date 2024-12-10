package dstu.inspection.repository.info;

import dstu.inspection.entity.info.CategoriesInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesInfoRepository extends JpaRepository<CategoriesInfo, Long> {
}
