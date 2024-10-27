package hackathon.hercules.component;

import hackathon.hercules.entity.File;
import hackathon.hercules.entity.Post;
import hackathon.hercules.entity.Tag;
import hackathon.hercules.entity.User;
import hackathon.hercules.service.FileService;
import java.util.List;

import hackathon.hercules.entity.*;
import hackathon.hercules.service.*;
import hackathon.hercules.service.PostService;
import hackathon.hercules.service.TagService;
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

    @Autowired
    MetaDataService metaDataService;

    @Autowired
    ConnectionService connectionService;

    //RoleService roleService;
    //ServerService serverService;
    //PostService postService;

    @Autowired
    private PostService postService;

    @Autowired
    private TagService tagService;

    @Override
    public void run(String... args) {
        FileEntity file = new FileEntity();
        file.setPath("test");
        fileService.saveFile(file);

        UserEntity user = new UserEntity();
        user.setEmail("JaneDoe@example.com");
        userService.createUser(user);

        ConnectionTypeEntity author_type = new ConnectionTypeEntity();
        author_type.setName("author");

        List<UserEntity> users = userService.getAllUsers();
        users.forEach((u) -> System.out.println(u.getEmail()));
        PostEntity post = new PostEntity();

        post.setText("Im Bereich der Entwicklung von Elektromotoren wird Bildsegmentierung eingesetzt, um CAD-Modelle effizienter zu gestalten und Konstruktionsprozesse zu optimieren. Durch die automatisierte Segmentierung lassen sich einzelne Komponenten, wie Rotoren und Statoren, präzise isolieren. Dies erleichtert die Analyse und Optimierung, da Entwickler gezielt an bestimmten Bauteilen arbeiten und die Funktionalität des gesamten Systems verbessern können. Dadurch wird der Entwicklungsprozess deutlich beschleunigt und die Qualität erhöht.");

        metaDataService.createMetaData(post.createMetaData("title", "Bildsegmentierung für CAD-Modelle von E-Antrieben"));
        connectionService.createConnection(user.createConnection(post, author_type));

        postService.updatePost(post);
    }

}