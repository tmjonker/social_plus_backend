package com.tmjonker.socialmediabackend.services;

import com.tmjonker.socialmediabackend.entities.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordManagementService {

    private PasswordEncoder passwordEncoder;
    private CustomUserDetailsService customUserDetailsService;

    public PasswordManagementService(PasswordEncoder passwordEncoder, CustomUserDetailsService customUserDetailsService) {

        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }

    public ResponseEntity<?> changePassword(String username, String newPassword) {

        User user = (User) customUserDetailsService.loadUserByUsername(username);

        if (user == null)
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);

        user.setPassword(passwordEncoder.encode(newPassword));
        customUserDetailsService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public boolean validatePassword(String username, String oldPassword) {

        User user = (User) customUserDetailsService.loadUserByUsername(username);

        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    public String encodePassword(String password) {

        return passwordEncoder.encode(password);
    }
}
