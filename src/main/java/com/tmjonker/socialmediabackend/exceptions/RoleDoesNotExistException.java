package com.tmjonker.socialmediabackend.exceptions;

import org.springframework.security.core.AuthenticationException;

public class RoleDoesNotExistException extends AuthenticationException {

    public RoleDoesNotExistException(String name) {

        super("Role with name, " + name + ", does not exist in database.");
    }
}
