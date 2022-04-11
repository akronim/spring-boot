package com.example.demo.exception;

// see UserController.java :: getUserByIdV1
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
