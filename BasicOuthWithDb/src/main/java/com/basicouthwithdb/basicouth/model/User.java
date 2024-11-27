package com.basicouthwithdb.basicouth.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "USR")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ElementCollection(targetClass = ROLE.class, fetch = FetchType.EAGER)
    @JoinTable(
            name="USER_ROLE",
            joinColumns = @JoinColumn(name="USER_ID", referencedColumnName="ID")
    )
    @Enumerated(EnumType.STRING)
    private Set<ROLE> roles;

    public User() {
    }

    public User(String username, String password, Set<ROLE> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
