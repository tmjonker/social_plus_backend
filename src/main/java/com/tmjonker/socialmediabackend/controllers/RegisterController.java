package com.tmjonker.socialmediabackend.controllers;

import com.tmjonker.socialmediabackend.dto.UserDTO;
import com.tmjonker.socialmediabackend.dto.UserStorageDTO;
import com.tmjonker.socialmediabackend.entities.user.User;
import com.tmjonker.socialmediabackend.jwt.JwtResponse;
import com.tmjonker.socialmediabackend.jwt.JwtTokenUtil;
import com.tmjonker.socialmediabackend.services.CustomUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin("*")
@RestController
public class RegisterController {

    private CustomUserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;

    public RegisterController(CustomUserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {

        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/api/register")
    public ResponseEntity<?> postRegistration(@RequestBody UserDTO userDTO) {
        try {
            User user = userDetailsService.saveNewUser(userDTO);
            String token = jwtTokenUtil.generateToken(user);
            
            UserStorageDTO userStorageDTO = new UserStorageDTO(user);

            return ResponseEntity.ok(Map.of("token", "Bearer " +  new JwtResponse(token), "user", userStorageDTO));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
