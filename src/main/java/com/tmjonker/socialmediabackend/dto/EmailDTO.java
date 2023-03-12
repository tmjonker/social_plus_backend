package com.tmjonker.socialmediabackend.dto;

public class EmailDTO {

    public String email;

    public EmailDTO(String email) {
        this.email = email;
    }

    public EmailDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
