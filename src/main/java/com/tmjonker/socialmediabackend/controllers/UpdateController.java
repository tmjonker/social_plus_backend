package com.tmjonker.socialmediabackend.controllers;

import com.tmjonker.socialmediabackend.dto.UpdateUserDTO;
import com.tmjonker.socialmediabackend.dto.UserStorageDTO;
import com.tmjonker.socialmediabackend.entities.user.User;
import com.tmjonker.socialmediabackend.services.UpdateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
public class UpdateController {

    private UpdateService updateService;

    public UpdateController(UpdateService updateService) {

        this.updateService = updateService;
    }

    @PostMapping("/api/update")
    public ResponseEntity<?> postUpdateUser(@RequestBody UpdateUserDTO updateUserDTO) {

        try {
            UserStorageDTO userStorageDTO = new UserStorageDTO(updateService.updateUser(updateUserDTO));
            return ResponseEntity.ok(Map.of("user", userStorageDTO));
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
