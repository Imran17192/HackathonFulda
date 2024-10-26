package hackathon.hercules.component;

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
    private UserService userService;

    @Autowired
    private PostService postService;

    @Override
    public void run(String... args) {
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