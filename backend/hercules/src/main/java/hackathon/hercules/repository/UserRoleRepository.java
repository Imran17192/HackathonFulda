package hackathon.hercules.repository;

import hackathon.hercules.entity.UserRoleEntity;
import hackathon.hercules.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
}