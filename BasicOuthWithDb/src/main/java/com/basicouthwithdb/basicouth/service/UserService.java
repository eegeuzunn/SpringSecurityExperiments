package com.basicouthwithdb.basicouth.service;

import com.basicouthwithdb.basicouth.dto.UserPostRequest;
import com.basicouthwithdb.basicouth.model.User;
import com.basicouthwithdb.basicouth.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(UserPostRequest userPostRequest) {
        User user = new User(
                userPostRequest.getUsername(),
                passwordEncoder.encode(userPostRequest.getPassword()),
                userPostRequest.getRoles()
        );
        return userRepository.save(user);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }
}
