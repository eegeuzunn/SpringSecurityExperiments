package com.inmemauth.inmemoryauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @GetMapping("/public")
    public String publicPage(){
        return "This is a public page";
    }

}
