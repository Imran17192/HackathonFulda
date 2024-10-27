package hackathon.hercules.controller;

import hackathon.hercules.entity.Post;
import hackathon.hercules.entity.Tag;
import hackathon.hercules.service.AuthService;
import hackathon.hercules.service.FileService;
import hackathon.hercules.service.PostService;
import hackathon.hercules.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class MainController {
    private final AuthService authService;
    private final PostService postService;
    private final FileService fileService;
    private final TagService tagService;

    @Autowired
    public MainController(AuthService authService, PostService postService, FileService fileService,
                          TagService tagService) {
        this.authService = authService;
        this.postService = postService;
        this.fileService = fileService;
        this.tagService = tagService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, String> payload) {
        try {
            String firstName = payload.get("firstName");
            String middleName = payload.get("middleName");
            String lastName = payload.get("lastName");
            String email = payload.get("email");
            String phoneNumber = payload.get("phone");
            String password = payload.get("password");
            authService.registerUser(firstName, middleName, lastName, email, phoneNumber, password);

            return ResponseEntity.ok(new AuthResponse("User registered successfully", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse("Registration failed", false));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        try {
            String email = payload.get("email");
            String password = payload.get("password");
            authService.authenticateUser(email, password);
            return ResponseEntity.ok(new AuthResponse("Login successful", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("Invalid credentials", false));
        }
    }

    @PostMapping("/messages")
    public ResponseEntity<?> post(@RequestParam String subject, @RequestParam String content, @RequestParam List<String> tags,
                                  @RequestParam(required = false) List<MultipartFile> files) {
        try {
            Post post = new Post();
            post.setTitle(subject);
            post.setContent(content);
            postService.savePost(post);
            for (String tag : tags) {
                Tag tagEntity = new Tag();
                tagEntity.setName(tag);
                tagEntity.setPostId(post.getId());
                tagService.saveTag(tagEntity);
            }
            if (files != null) {
                for (MultipartFile file : files) {
                    Path path = Paths.get("src/main/resources/uploads/" + file.getOriginalFilename());
                    try (OutputStream os = java.nio.file.Files.newOutputStream(path)) {
                        os.write(file.getBytes());
                    }
                    hackathon.hercules.entity.File fileEntity = new hackathon.hercules.entity.File();
                    fileEntity.setName(file.getOriginalFilename());
                    fileEntity.setPath(path.toString());
                    fileEntity.setType(file.getContentType());
                    fileEntity.setSize(file.getSize());
                    fileEntity.setPostId(post.getId());
                    fileService.saveFile(fileEntity);
                }
            }
            return ResponseEntity.ok(new AuthResponse("Post created successfully", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse("Post creation failed", false));
        }
    }

    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to the Home Page!");
    }

    public record AuthResponse(String message, boolean success) {
    }
}
