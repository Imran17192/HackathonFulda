package hackathon.hercules.controller;

import hackathon.hercules.entity.Post;
import hackathon.hercules.entity.Tag;
import hackathon.hercules.entity.User;
import hackathon.hercules.service.*;
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
    private final UserService userService;

    @Autowired
    public MainController(AuthService authService, PostService postService, FileService fileService,
                          TagService tagService, UserService userService) {
        this.authService = authService;
        this.postService = postService;
        this.fileService = fileService;
        this.tagService = tagService;
        this.userService = userService;
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
    public ResponseEntity<?> post(@RequestParam String subject, @RequestParam String content,
                                  @RequestParam List<String> tags, @RequestParam String username,
                                  @RequestParam(required = false) List<MultipartFile> files) {
        try {

            User user = userService.getUserByEmail(username);

            Post post = new Post();
            post.setTitle(subject);
            post.setContent(content);
            if(user != null) {
                post.setAuthor(user.getFirstName() + " " + user.getLastName());
            }
            else {
                post.setAuthor("anonymous user");
            }
            postService.savePost(post);
            for (String tag : tags) {
                Tag tagEntity = new Tag();
                tagEntity.setName(tag);
                tagEntity.setPostId(post.getId());
                tagService.saveTag(tagEntity);
            }
            if (files != null) {
                for (MultipartFile file : files) {
                    Path path = Paths.get("src/main/resources/uploads/" + file.getOriginalFilename()
                            + "_" + post.getId());
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
            System.out.println("test0");
            userService.incrementTreeCount(userService.getUserByEmail(username).getId());
            return ResponseEntity.ok(new AuthResponse("Post created successfully", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse("Post creation failed", false));
        }
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getPosts() {
        try {
            List<Post> posts = postService.getAllPosts();
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse("Failed to fetch posts", false));
        }
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        try {
            Post post = postService.getPostById(id);
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse("Failed to fetch post", false));
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse("Failed to fetch users", false));
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        try {
            return ResponseEntity.ok(userService.getUserByEmail(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse("Failed to fetch user", false));
        }
    }

    // change city, country of user
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody Map<String, String> payload) {
        try {
            String city = payload.get("city");
            String country = payload.get("country");
            String bio = payload.get("bio");
            System.out.println(id);
            User user = userService.getUserById(Long.valueOf(id));
            user.setCity(city);
            user.setCountry(country);
            user.setBio(bio);
            userService.updateUser(user);
            return ResponseEntity.ok(new AuthResponse("User updated successfully", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse("Failed to update user", false));
        }
    }

    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to the Home Page!");
    }

    public record AuthResponse(String message, boolean success) {
    }
}
