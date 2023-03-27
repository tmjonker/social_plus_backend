package com.tmjonker.socialmediabackend.services;

import com.tmjonker.socialmediabackend.dto.UpdateUserDTO;
import com.tmjonker.socialmediabackend.entities.user.User;
import com.tmjonker.socialmediabackend.utility.S3Util;
import org.springframework.stereotype.Service;

@Service
public class UpdateService {

    private CustomUserDetailsService userDetailsService;
    private PasswordManagementService passwordManagementService;

    public UpdateService(CustomUserDetailsService userDetailsService, PasswordManagementService passwordManagementService) {

        this.userDetailsService = userDetailsService;
        this.passwordManagementService = passwordManagementService;
    }

    public User updateUser(UpdateUserDTO updateUserDTO) throws Exception {

        S3Util s3Util = new S3Util();
        s3Util.putS3Object(updateUserDTO.getImage().getBytes(), updateUserDTO.getUsername());

        User user = userDetailsService.getUserByUsername(updateUserDTO.getUsername());
        user.setEmail(updateUserDTO.getEmail());
        user.setFirstName(updateUserDTO.getFirstName());
        user.setLastName(updateUserDTO.getLastName());
        user.setUsername(updateUserDTO.getUsername());

        return userDetailsService.saveUser(user);
    }
}
