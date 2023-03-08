package com.tmjonker.socialmediabackend.services;

import com.tmjonker.socialmediabackend.entities.role.Role;
import com.tmjonker.socialmediabackend.exceptions.RoleDoesNotExistException;
import com.tmjonker.socialmediabackend.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {

        this.roleRepository = roleRepository;
    }

    public void saveRole(Role role) {

        roleRepository.save(role);
    }

    public boolean roleExists(Role role) {

        return roleRepository.existsByName(role.getName());
    }

    public Role getRole(String name) {

        return roleRepository.findByName(name).orElseThrow(() -> new RoleDoesNotExistException(name));
    }
}
