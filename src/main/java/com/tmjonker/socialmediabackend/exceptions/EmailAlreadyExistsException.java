package com.tmjonker.socialmediabackend.exceptions;

import org.springframework.security.core.AuthenticationException;

public class EmailAlreadyExistsException extends AuthenticationException {

    public EmailAlreadyExistsException(String email) {

        super("User with email, " + email + ", already exists in database.");
    }
}