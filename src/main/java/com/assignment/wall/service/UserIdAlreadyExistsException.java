package com.assignment.wall.service;


public class UserIdAlreadyExistsException extends RuntimeException{
    public UserIdAlreadyExistsException(String message) {
        super(message);
    }
}
