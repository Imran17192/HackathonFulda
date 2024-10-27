package hackathon.hercules.service;

import hackathon.hercules.entity.ObjectEntity;
import hackathon.hercules.repository.MetaDataRepository;
import hackathon.hercules.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectService {

    @Autowired
    private ObjectRepository repository;

    public ObjectEntity createObject(ObjectEntity o) {
        return repository.save(o);
    }

    public List<ObjectEntity> getAllObjects() {
        return repository.findAll();
    }

    public ObjectEntity getObjectById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ObjectEntity updateObject(ObjectEntity o) {
        return repository.save(o);
    }

    public void deleteObject(Long id) {
        repository.deleteById(id);
    }
}