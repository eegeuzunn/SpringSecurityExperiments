package com.basicouthwithdb.basicouth.controller;

import com.basicouthwithdb.basicouth.dto.UserPostRequest;
import com.basicouthwithdb.basicouth.model.User;
import com.basicouthwithdb.basicouth.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
