package com.tmjonker.socialmediabackend.controllers;

import com.tmjonker.socialmediabackend.dto.UserDTO;
import com.tmjonker.socialmediabackend.entities.user.User;
import com.tmjonker.socialmediabackend.services.CustomUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class RegisterController {

    CustomUserDetailsService userDetailsService;

    public RegisterController(CustomUserDetailsService userDetailsService) {

        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> postRegistration(@RequestBody UserDTO userDTO) {
        try {
            User user = userDetailsService.saveNewUser(userDTO);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
