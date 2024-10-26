package hackathon.hercules.component;

import java.util.List;

import hackathon.hercules.entity.UserEntity;
import hackathon.hercules.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    UserService userService;
    FileService fileService;
    RoleService roleService;
    ServerService serverService;
    PostService postService;


    @Override
    public void run(String... args) {

        //UserEntity user = new UserEntity();
        ///user.setUserName("Jane Doe");

        //userService.createUser(user);

        //List<UserEntity> users = userService.getAllUsers();
        //users.forEach((u) -> System.out.println(u.getUserName()));


    }

}