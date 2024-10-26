package hackathon.hercules.service;

import hackathon.hercules.entity.ServerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hackathon.hercules.repository.ServerRepository;

import java.util.List;

@Service
public class ServerService {

    @Autowired
    private ServerRepository repository;

    public ServerEntity createServer(ServerEntity server) {
        return repository.save(server);
    }

    public List<ServerEntity> getAllServers() {
        return repository.findAll();
    }

    public ServerEntity getServerById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ServerEntity updateServer(ServerEntity server) {
        return repository.save(server);
    }

    public void deleteServer(Long id) {
        repository.deleteById(id);
    }
}