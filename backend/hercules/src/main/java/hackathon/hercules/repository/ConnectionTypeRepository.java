package hackathon.hercules.repository;

import hackathon.hercules.entity.ConnectionTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionTypeRepository extends JpaRepository<ConnectionTypeEntity, Long> {
}