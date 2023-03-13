package com.tmjonker.socialmediabackend.dto;

import com.tmjonker.socialmediabackend.entities.user.User;

public class UserStorageDTO {

    private String email;
    private String username;
    private String firstName;
    private String lastName;

    public UserStorageDTO() {
    }

    public UserStorageDTO(User user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
