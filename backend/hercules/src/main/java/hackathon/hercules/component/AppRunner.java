package hackathon.hercules.component;

import hackathon.hercules.entity.User;
import hackathon.hercules.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) {
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
    }

}