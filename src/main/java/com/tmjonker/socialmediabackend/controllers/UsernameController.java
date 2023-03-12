package com.tmjonker.socialmediabackend.controllers;

import com.tmjonker.socialmediabackend.dto.UsernameDTO;
import com.tmjonker.socialmediabackend.services.CustomUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UsernameController {

    private CustomUserDetailsService userDetailsService;

    public UsernameController(CustomUserDetailsService userDetailsService) {

        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/username")
    public ResponseEntity<?> checkUsernameExists(@RequestBody UsernameDTO username) {

        boolean exists = userDetailsService.userExistsByUsername(username.getUsername());

        if (!exists) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
