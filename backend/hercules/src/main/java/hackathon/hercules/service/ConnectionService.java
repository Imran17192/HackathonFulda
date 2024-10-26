package hackathon.hercules.service;

import hackathon.hercules.entity.ConnectionEntity;
import hackathon.hercules.repository.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionService {

    @Autowired
    private ConnectionRepository repository;

    public ConnectionEntity createConnection(ConnectionEntity connection) {
        return repository.save(connection);
    }

    public List<ConnectionEntity> getAllConnections() {
        return repository.findAll();
    }

    public ConnectionEntity getConnectionById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ConnectionEntity updateConnection(ConnectionEntity connection) {
        return repository.save(connection);
    }

    public void deleteConnection(Long id) {
        repository.deleteById(id);
    }
}