package com.acs560.ShareTaxi.controllers;

import com.acs560.ShareTaxi.models.CustomUser;
import com.acs560.ShareTaxi.services.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private CustomUserService customUserService;

    /**
     * Endpoint to handle user registration.
     *
     * @param request the registration request containing username and password
     * @return ResponseEntity with registered user details or error message
     */
    @PostMapping(value="/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> registerUser(@RequestBody RegisterRequest request) {
        try {
            CustomUser newUser = customUserService.registerUser(request.getUsername(),request.getEmail(),request.getPassword());
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("User registered successfully", newUser));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(e.getMessage(), null));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseMessage> loginUser(@RequestBody LoginRequest request) {
        try {
            CustomUser authenticatedUser = customUserService.authenticateUser(request.getUsername(), request.getPassword());
            return ResponseEntity.ok(new ResponseMessage("Login successful", authenticatedUser));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessage(e.getMessage(), null));
        }
    }

    // Request classes for registration and login
    public static class RegisterRequest {
        private String username;
        private String password;
        private String email;

        // Getters and setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getEmail() { return email; }
        public void setEmail(String	email) { this.email = email; }
        
    }

    public static class LoginRequest {
        private String username;
        private String password;

        // Getters and setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    // Helper method to create response messages
    private Object createResponseMessage(String message, CustomUser user) {
        return new ResponseMessage(message, user);
    }

    // Response message structure
    private static class ResponseMessage {
        private String message;
        private CustomUser user;

        public ResponseMessage(String message, CustomUser user) {
            this.message = message;
            this.user = user;
        }

        public String getMessage() {
            return message;
        }

        public CustomUser getUser() {
            return user;
        }
    }
}

