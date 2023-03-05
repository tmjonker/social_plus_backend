package com.tmjonker.socialmediabackend.services;

import com.tmjonker.socialmediabackend.dto.UserDTO;
import com.tmjonker.socialmediabackend.entities.user.User;
import com.tmjonker.socialmediabackend.exceptions.UsernameAlreadyExistsException;
import com.tmjonker.socialmediabackend.repositories.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordManagementService passwordManagementService;

    public CustomUserDetailsService(UserRepository userRepository, @Lazy PasswordManagementService passwordManagementService) {

        this.userRepository = userRepository;
        this.passwordManagementService = passwordManagementService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username"));
    }

    public User saveNewUser(UserDTO userDTO) throws UsernameAlreadyExistsException {

        boolean exists = userRepository.existsByUsername(userDTO.getUsername());
        if (!exists) {
            User user = new User(userDTO.getUsername(), passwordManagementService.encodePassword(userDTO.getPassword1()));
            return userRepository.save(user);
        } else {
            throw new UsernameAlreadyExistsException(userDTO.getUsername());
        }
    }

    public User saveUser(User user) {

        return userRepository.save(user);
    }
}
