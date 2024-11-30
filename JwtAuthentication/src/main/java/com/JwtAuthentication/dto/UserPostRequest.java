package com.JwtAuthentication.dto;

import com.JwtAuthentication.model.ROLE;

import java.util.Set;

public class UserPostRequest {
    private String username;
    private String password;

    private Set<ROLE> roles;

    public UserPostRequest() {
    }

    public UserPostRequest(String username, String password, Set<ROLE> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ROLE> getRoles() {
        return roles;
    }

    public void setRoles(Set<ROLE> roles) {
        this.roles = roles;
    }
}
