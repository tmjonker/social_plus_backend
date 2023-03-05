package com.tmjonker.socialmediabackend.services;

import com.tmjonker.socialmediabackend.entities.role.Role;
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
}
