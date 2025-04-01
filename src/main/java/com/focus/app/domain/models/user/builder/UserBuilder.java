package com.focus.app.domain.models.user.builder;

import com.focus.app.domain.models.user.User;
import com.focus.app.shared.utils.EmailAddress;

import java.util.UUID;

public class UserBuilder {
    private UUID id;
    private String username;
    private EmailAddress email;
    private String password;

    public UserBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder email(EmailAddress email) {
        this.email = email;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public User build() {
        return new User();
    }

    public String getEmail() {
        return this.email.getEmail();
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public UUID getId() {
        return this.id;
    }
}
