package hackathon.hercules.service;

import hackathon.hercules.entity.User;
import hackathon.hercules.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String firstName, String middleName, String lastName,
                             String email, String phoneNumber, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(passwordEncoder.encode(password));
        user.setTreeCount(0);
        user.setBio("Here is my bio. Double click to edit.");
        user.setCity("");
        user.setCountry("");
        user.setProfilePicture("");
        userRepository.save(user);
    }

    public User authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return user;
    }
}
