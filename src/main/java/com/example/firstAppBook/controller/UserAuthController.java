package com.example.firstAppBook.controller;

import com.example.firstAppBook.dto.UserDTO;
import com.example.firstAppBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class UserAuthController {

    private UserService userService;

    @Autowired
    public UserAuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO)
    {
        try {
            UserDTO registeredUser = userService.register(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of(
                            "username", registeredUser.getUsername(),
                            "role", registeredUser.getRole().name(),
                            "message", "User registered successfully"
                    ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO){
        try {
            String token = userService.loginWithJwt(userDTO.getUsername(), userDTO.getPassword());
            // Extract role from userService or JWT if needed
            UserDTO user = userService.findByUsername(userDTO.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found after login"));
            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "role", user.getRole().name()
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid credentials"));
        }
    }

    // existing register, login methods...

    @GetMapping("/profile")
    public ResponseEntity<?> profile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        String username = auth.getName(); // get username from security context
        UserDTO userDTO = userService.findByUsername(username)
                .orElse(null);

        if (userDTO == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        return ResponseEntity.ok(new UserProfileResponse(userDTO.getUsername(), userDTO.getRole().name()));
    }

    // DTO for profile response
    private static class UserProfileResponse {
        private String username;
        private String role;

        public UserProfileResponse(String username, String role) {
            this.username = username;
            this.role = role;
        }

        public String getUsername() {
            return username;
        }

        public String getRole() {
            return role;
        }
    }


}
