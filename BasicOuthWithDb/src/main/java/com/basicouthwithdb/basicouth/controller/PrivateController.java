package com.basicouthwithdb.basicouth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("private")
public class PrivateController {

    @GetMapping
    public String getPrivate() {
        return "This is private endpoint!";
    }

    @GetMapping("user")
    public String getUser() {
        return "This is private user endpoint!";
    }

    @GetMapping("admin")
    public String getAdmin() {
        return "This is private admin endpoint!";
    }


}
