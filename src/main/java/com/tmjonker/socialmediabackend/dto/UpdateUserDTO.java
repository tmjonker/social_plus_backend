package com.tmjonker.socialmediabackend.dto;

import org.springframework.web.multipart.MultipartFile;

public class UpdateUserDTO {

    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private MultipartFile image;

    public UpdateUserDTO(String email, String username, String firstName, String lastName, MultipartFile image) {
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
    }

    public UpdateUserDTO() {
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
