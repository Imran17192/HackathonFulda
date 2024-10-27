package hackathon.hercules.component;

import hackathon.hercules.entity.File;
import hackathon.hercules.entity.Post;
import hackathon.hercules.entity.Tag;
import hackathon.hercules.entity.User;
import hackathon.hercules.service.FileService;
import hackathon.hercules.service.PostService;
import hackathon.hercules.service.TagService;
import hackathon.hercules.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Autowired
    private TagService tagService;

    @Override
    public void run(String... args) {
        for (Post p : postService.getAllPosts()) {
            System.out.println(p);
        }

        for (User u : userService.getAllUsers()) {
            System.out.println(u);
        }

        for (File f : fileService.getAllFiles()) {
            System.out.println(f);
        }

        for (Tag t : tagService.getAllTags()) {
            System.out.println(t);
        }
    }

}