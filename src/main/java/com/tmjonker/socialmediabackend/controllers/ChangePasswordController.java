package com.tmjonker.socialmediabackend.controllers;

import com.tmjonker.socialmediabackend.dto.ChangePasswordDTO;
import com.tmjonker.socialmediabackend.services.PasswordManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ChangePasswordController {

    private final PasswordManagementService passwordManagementService;

    public ChangePasswordController(PasswordManagementService passwordManagementService) {
        this.passwordManagementService = passwordManagementService;
    }

    @PostMapping("/change-pw")
    public ResponseEntity<?> postChangePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {

        return passwordManagementService.changePassword(changePasswordDTO);
    }
}
