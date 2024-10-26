package hackathon.hercules.service;

import hackathon.hercules.entity.MetaDataEntity;
import hackathon.hercules.repository.MetaDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaDataService {

    @Autowired
    private MetaDataRepository repository;

    public MetaDataEntity createMetaData(MetaDataEntity metaData) {
        return repository.save(metaData);
    }

    public List<MetaDataEntity> getAllMetaDatas() {
        return repository.findAll();
    }

    public MetaDataEntity getMetaDataById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public MetaDataEntity updateMetaData(MetaDataEntity metaData) {
        return repository.save(metaData);
    }

    public void deleteMetaData(Long id) {
        repository.deleteById(id);
    }
}