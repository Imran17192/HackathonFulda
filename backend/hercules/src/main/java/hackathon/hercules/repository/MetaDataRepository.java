package hackathon.hercules.repository;

import hackathon.hercules.entity.MetaDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaDataRepository extends JpaRepository<MetaDataEntity, Long> {
}