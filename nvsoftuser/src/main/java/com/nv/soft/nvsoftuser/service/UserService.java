package com.nv.soft.nvsoftuser.service;


import com.nv.soft.nvsoftuser.entity.User;
import com.nv.soft.nvsoftuser.modal.LoginRequest;
import com.nv.soft.nvsoftuser.modal.RegisterRequest;
import com.nv.soft.nvsoftuser.modal.UserResponse;
import com.nv.soft.nvsoftuser.repo.UserRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;

    public UserResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
           // throw new UserAlreadyExistsException("Email already registered");
        }

        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .build();

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );
    }

    public UserResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

     /*   if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }*/

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
