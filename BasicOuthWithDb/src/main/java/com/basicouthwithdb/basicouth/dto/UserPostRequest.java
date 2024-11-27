package com.basicouthwithdb.basicouth.dto;

import com.basicouthwithdb.basicouth.model.ROLE;
import jakarta.persistence.*;

import java.util.Set;

public class UserPostRequest {

    private String username;

    private String password;

    private Set<ROLE> roles;

    public UserPostRequest() {
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
