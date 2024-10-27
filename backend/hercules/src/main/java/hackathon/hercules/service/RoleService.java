package hackathon.hercules.service;

import hackathon.hercules.entity.RoleEntity;
import hackathon.hercules.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public RoleEntity createRole(RoleEntity role) {
        return repository.save(role);
    }

    public List<RoleEntity> getAllRoles() {
        return repository.findAll();
    }

    public RoleEntity getRoleById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public RoleEntity updateRole(RoleEntity role) {
        return repository.save(role);
    }

    public void deleteRole(Long id) {
        repository.deleteById(id);
    }
}