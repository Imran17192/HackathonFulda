package hackathon.hercules.repository;

import hackathon.hercules.entity.MetaDataEntity;
import hackathon.hercules.entity.ObjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaDataRepository extends JpaRepository<MetaDataEntity, Long> {

    public MetaDataEntity findByOwnerObjectAndKey(ObjectEntity objectOwner, String key);
}