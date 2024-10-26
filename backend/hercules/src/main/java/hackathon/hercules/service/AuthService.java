package hackathon.hercules.service;

import hackathon.hercules.entity.UserEntity;
import hackathon.hercules.repository.MetaDataRepository;
import hackathon.hercules.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final MetaDataRepository metaDataRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public AuthService(UserRepository userRepository, MetaDataRepository metaDataRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.metaDataRepository = metaDataRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String firstName, String middleName, String lastName,
                             String email, String phoneNumber, String password) {
        UserEntity user = new UserEntity();
        metaDataRepository.save(user.createMetaData("firstName", firstName));
        metaDataRepository.save(user.createMetaData("middleName", middleName));
        metaDataRepository.save(user.createMetaData("lastName", lastName));
        metaDataRepository.save(user.createMetaData("phoneNumber", phoneNumber));

        user.setPassword(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public void authenticateUser(String email, String password) {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
    }
}
