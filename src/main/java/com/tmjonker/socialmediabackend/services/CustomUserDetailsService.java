package com.tmjonker.socialmediabackend.services;

import com.tmjonker.socialmediabackend.constants.Constants;
import com.tmjonker.socialmediabackend.dto.UserDTO;
import com.tmjonker.socialmediabackend.entities.role.Role;
import com.tmjonker.socialmediabackend.entities.user.User;
import com.tmjonker.socialmediabackend.exceptions.UsernameAlreadyExistsException;
import com.tmjonker.socialmediabackend.repositories.RoleRepository;
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
    private RoleService roleService;

    public CustomUserDetailsService(UserRepository userRepository, @Lazy PasswordManagementService passwordManagementService,
                                    RoleService roleService) {

        this.userRepository = userRepository;
        this.passwordManagementService = passwordManagementService;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("username"));
    }

    public User saveNewUser(UserDTO userDTO) throws UsernameAlreadyExistsException {

        Role role = new Role();

        try {
            role = roleService.getRole(Constants.USER);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        boolean exists = userRepository.existsByUsername(userDTO.getUsername());
        if (!exists) {
            User user = new User(userDTO.getEmail(), userDTO.getUsername(), userDTO.getFirstName(),
                    userDTO.getLastName(), passwordManagementService.encodePassword(userDTO.getPassword1()));
            user.addRole(role);
            return userRepository.save(user);
        } else {
            throw new UsernameAlreadyExistsException(userDTO.getUsername());
        }
    }

    public User saveUser(User user) {

        return userRepository.save(user);
    }
}
