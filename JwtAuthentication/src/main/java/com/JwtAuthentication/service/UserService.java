package com.JwtAuthentication.service;

import com.JwtAuthentication.dto.UserPostRequest;
import com.JwtAuthentication.model.User;
import com.JwtAuthentication.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    public User saveUser(UserPostRequest userPostRequest) {
         User user = new User(userPostRequest.getUsername(), passwordEncoder.encode(userPostRequest.getPassword()), userPostRequest.getRoles());
        System.out.println("Roles being saved: " + user.getRoles());
        return userRepository.save(user);
    }
}
