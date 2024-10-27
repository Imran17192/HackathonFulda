package hackathon.hercules.repository;

import hackathon.hercules.entity.ConnectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRepository extends JpaRepository<ConnectionEntity, Long> {
}