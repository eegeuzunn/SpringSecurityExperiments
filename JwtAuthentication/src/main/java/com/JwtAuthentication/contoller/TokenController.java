package com.JwtAuthentication.contoller;

import com.JwtAuthentication.dto.TokenRequest;
import com.JwtAuthentication.service.JwtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {

    private final JwtService jwtService;

    public TokenController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping
    public String getAToken(@RequestBody TokenRequest tokenRequest){
        return jwtService.generateAccessToken(tokenRequest.getUsername());
    }
}
