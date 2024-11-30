package com.JwtAuthentication.contoller;

import com.JwtAuthentication.dto.UserPostRequest;
import com.JwtAuthentication.model.User;
import com.JwtAuthentication.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("post/user")
    public String postUser(@RequestBody UserPostRequest userPostRequest) {
        User user = userService.saveUser(userPostRequest);
        if(user != null) {
            return "User created successfully!";
        } else {
            return "User creation failed!";
        }
    }

    @GetMapping("/user")
    public String publicUserEndpoint(){
        return "This is User enpoint";
    }

    @GetMapping("/admin")
    public String publicAdminEndpoint(){
        return "This is Admin enpoint";
    }
}
