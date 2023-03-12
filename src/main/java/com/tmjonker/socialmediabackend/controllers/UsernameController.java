package com.tmjonker.socialmediabackend.controllers;

import com.tmjonker.socialmediabackend.services.CustomUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public class UsernameController {

    private CustomUserDetailsService userDetailsService;

    public UsernameController(CustomUserDetailsService userDetailsService) {

        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/username")
    public ResponseEntity<?> checkUsernameExists(String username) {

        boolean exists = userDetailsService.userExistsByUsername(username);

        if (!exists) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
