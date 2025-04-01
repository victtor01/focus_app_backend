package com.focus.app.domain.models.user;

import com.focus.app.domain.models.user.builder.UserBuilder;

import java.util.UUID;

public class User {

    private UUID id;

    private String username;

    private String email;

    private String password;

    public User(UUID id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(UserBuilder userBuilder) {
        this.id = userBuilder.getId();
        this.username = userBuilder.getUsername();
        this.email = userBuilder.getEmail();
        this.password = userBuilder.getPassword();
    }

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
