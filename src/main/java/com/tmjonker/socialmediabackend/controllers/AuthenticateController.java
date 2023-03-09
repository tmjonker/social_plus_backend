package com.tmjonker.socialmediabackend.controllers;

import com.tmjonker.socialmediabackend.entities.user.User;
import com.tmjonker.socialmediabackend.jwt.JwtRequest;
import com.tmjonker.socialmediabackend.jwt.JwtResponse;
import com.tmjonker.socialmediabackend.jwt.JwtTokenUtil;
import com.tmjonker.socialmediabackend.services.AuthenticationService;
import com.tmjonker.socialmediabackend.services.CustomUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@CrossOrigin
public class AuthenticateController {

    private AuthenticationService authenticationService;
    private JwtTokenUtil jwtTokenUtil;
    private CustomUserDetailsService userDetailsService;

    public AuthenticateController(AuthenticationService authenticationService, JwtTokenUtil jwtTokenUtil,
                                  CustomUserDetailsService userDetailsService) {

        this.authenticationService = authenticationService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        try {
            authenticationService.authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getEmail());

            final String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(Map.of("token", new JwtResponse(token), "user", (User) userDetails));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
