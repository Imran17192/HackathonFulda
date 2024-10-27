package hackathon.hercules.service;

import hackathon.hercules.entity.ConnectionEntity;
import hackathon.hercules.entity.ConnectionTypeEntity;
import hackathon.hercules.repository.ConnectionRepository;
import hackathon.hercules.repository.ConnectionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionTypeService {

    @Autowired
    private ConnectionTypeRepository repository;

    public ConnectionTypeEntity createConnection(ConnectionTypeEntity connection) {
        return repository.save(connection);
    }

    public List<ConnectionTypeEntity> getAllConnectionTypes() {
        return repository.findAll();
    }

    public ConnectionTypeEntity getConnectionTypeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ConnectionTypeEntity updateConnectionType(ConnectionTypeEntity connection) {
        return repository.save(connection);
    }

    public void deleteConnection(Long id) {
        repository.deleteById(id);
    }
}