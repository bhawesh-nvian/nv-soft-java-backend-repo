package com.nv.soft.nvsoftuser.controller;


import com.nv.soft.nvsoftuser.modal.LoginRequest;
import com.nv.soft.nvsoftuser.modal.RegisterRequest;
import com.nv.soft.nvsoftuser.modal.UserResponse;
import com.nv.soft.nvsoftuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/user")
public class AuthController {

    private final UserService userService;

    // ✅ Constructor Injection
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // GET endpoint for testing
    @GetMapping("/users")
    public ResponseEntity<String> users() {
        return ResponseEntity.ok("Hello, User!");
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) {
        UserResponse response = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest request) {
        UserResponse response = userService.login(request);
        return ResponseEntity.ok(response); // 200 OK
    }
}
