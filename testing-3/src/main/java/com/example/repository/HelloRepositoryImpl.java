package com.example.repository;

import org.springframework.stereotype.Repository;

@Repository
public class HelloRepositoryImpl implements HelloRepository {
    @Override
    public String get() {
        return "Hello JUnit 5";
    }

    @Override
    public String getMessage() {
        return "Lorem ipsum dolor sit amet.";
    }

    @Override
    public String getFormattedMessage() {
        return "Hi there! " + getMessage();
    }

    @Override
    public void logMessage(String message) {
        System.out.println(message);
    }
}
