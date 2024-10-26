package hackathon.hercules.component;

import java.util.List;

import hackathon.hercules.entity.FileEntity;
import hackathon.hercules.entity.UserEntity;
import hackathon.hercules.service.*;
import hackathon.hercules.entity.Post;
import hackathon.hercules.entity.User;
import hackathon.hercules.service.PostService;
import hackathon.hercules.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    UserService userService;
    //ObjectService objectService;

    @Autowired
    FileService fileService;
    //RoleService roleService;
    //ServerService serverService;
    //PostService postService;

    @Autowired
    private PostService postService;

    @Override
    public void run(String... args) {
        FileEntity file = new FileEntity();
        file.setPath("test");
        fileService.createFile(file);

        UserEntity user = new UserEntity();
        user.setUserName("Jane Doe");
        userService.createUser(user);


        List<UserEntity> users = userService.getAllUsers();
        users.forEach((u) -> System.out.println(u.getUserName()));
        Post post = new Post();
        post.setTitle("Bildsegmentierung für CAD-Modelle von E-Antrieben");
        post.setContent("Im Bereich der Entwicklung von Elektromotoren wird Bildsegmentierung eingesetzt, um CAD-Modelle effizienter zu gestalten und Konstruktionsprozesse zu optimieren. Durch die automatisierte Segmentierung lassen sich einzelne Komponenten, wie Rotoren und Statoren, präzise isolieren. Dies erleichtert die Analyse und Optimierung, da Entwickler gezielt an bestimmten Bauteilen arbeiten und die Funktionalität des gesamten Systems verbessern können. Dadurch wird der Entwicklungsprozess deutlich beschleunigt und die Qualität erhöht.");
        post.setAuthor("Max Müller");
        postService.savePost(post);

        for (Post p : postService.getAllPosts()) {
            System.out.println(p);
        }
    }

}