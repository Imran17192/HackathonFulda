package hackathon.hercules.service;

import hackathon.hercules.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hackathon.hercules.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity updateUser(UserEntity user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public boolean isUserExists(Long id) {
        return userRepository.existsById(id);
    }

    public boolean isUserExistsByEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public void incrementTreeCount(Long id) {
        User user = userRepository.findById(id).orElse(null);
        System.out.println("test1");
        if (user != null) {
            user.setTreeCount(user.getTreeCount() + 1);
            System.out.println("test2");
            userRepository.save(user);
            System.out.println("test3");
        }
    }
}
