package com.tmjonker.socialmediabackend.repositories;

import com.tmjonker.socialmediabackend.entities.role.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
