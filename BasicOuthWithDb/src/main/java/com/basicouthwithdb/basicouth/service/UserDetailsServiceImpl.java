package com.basicouthwithdb.basicouth.service;

import com.basicouthwithdb.basicouth.model.User;
import com.basicouthwithdb.basicouth.security.CustomUserDetails;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.getUser(username);
        return new CustomUserDetails(user);
    }
}
