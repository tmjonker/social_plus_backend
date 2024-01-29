package com.tmjonker.socialmediabackend.dto;

public class ChangePasswordDTO {

    private String username;
    private String password;

    public ChangePasswordDTO(String username, String password) {
        this.username = username;
        this.password = password;
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

    public void setPassword(String password1) {
        this.password = password1;
    }
}
