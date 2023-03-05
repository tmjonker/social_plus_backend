package com.tmjonker.socialmediabackend.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UsernameAlreadyExistsException extends AuthenticationException {

    public UsernameAlreadyExistsException(String username) {

        super("User with username, " + username + ", already exists in database.");
    }
}
