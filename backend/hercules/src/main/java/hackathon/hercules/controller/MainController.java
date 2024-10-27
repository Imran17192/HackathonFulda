package hackathon.hercules.controller;

import hackathon.hercules.entity.Post;
import hackathon.hercules.service.AuthService;
import hackathon.hercules.service.PostService;
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
    private final RequestContextFilter requestContextFilter;

    @Autowired
    public MainController(AuthService authService, PostService postService, RequestContextFilter requestContextFilter) {
        this.authService = authService;
        this.postService = postService;
        this.requestContextFilter = requestContextFilter;
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
            List<String> fileNames = new ArrayList<>();
            System.out.println("here");
            if (files != null) {
                for (MultipartFile file : files) {
                    fileNames.add(file.getOriginalFilename());
                    Path path = Paths.get("src/main/resources/uploads/" + file.getOriginalFilename());
                    try (OutputStream os = java.nio.file.Files.newOutputStream(path)) {
                        os.write(file.getBytes());
                    }
                }
            }
            System.out.println("here");
            Post post = new Post();
            post.setTitle(subject);
            post.setContent(content);
            post.setTags(tags);
            post.setFiles(fileNames);
            postService.savePost(post);
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
