package com.tmjonker.socialmediabackend.repositories;

import com.tmjonker.socialmediabackend.entities.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}