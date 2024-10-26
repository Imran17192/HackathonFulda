package hackathon.hercules.controller;

import hackathon.hercules.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
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
            String username = payload.get("username");
            String password = payload.get("password");
            authService.authenticateUser(username, password);
            return ResponseEntity.ok(new AuthResponse("Login successful", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("Invalid credentials", false));
        }
    }

    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to the Home Page!");
    }

    public static class AuthResponse {
        private String message;
        private boolean success;

        public AuthResponse(String message, boolean success) {
            this.message = message;
            this.success = success;
        }

        public String getMessage() { return message; }
        public boolean isSuccess() { return success; }
    }
}
