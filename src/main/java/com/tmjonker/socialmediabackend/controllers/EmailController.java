package com.tmjonker.socialmediabackend.controllers;

import com.tmjonker.socialmediabackend.dto.EmailDTO;
import com.tmjonker.socialmediabackend.services.CustomUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
public class EmailController {

    private CustomUserDetailsService userDetailsService;

    public EmailController(CustomUserDetailsService userDetailsService) {

        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/api/email")
    public ResponseEntity<?> checkEmailExists(@RequestBody EmailDTO email) {

        boolean exists = userDetailsService.userExistsByEmail(email.getEmail());
        if (!exists) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
