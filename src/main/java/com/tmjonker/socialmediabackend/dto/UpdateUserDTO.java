package com.tmjonker.socialmediabackend.dto;

import org.springframework.web.multipart.MultipartFile;

public class UpdateUserDTO {

    private String username;
    private String firstName;
    private String lastName;
    private String image;

    public UpdateUserDTO(String username, String firstName, String lastName, String image) {

        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
    }

    public UpdateUserDTO() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
