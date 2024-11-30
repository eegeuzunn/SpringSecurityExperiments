package com.JwtAuthentication.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.security.core.GrantedAuthority;


public enum ROLE implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

    @JsonCreator
    public static ROLE fromValue(String value) {
        return ROLE.valueOf(value.toUpperCase());
    }
}
