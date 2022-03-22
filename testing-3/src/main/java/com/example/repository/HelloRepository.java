package com.example.repository;


public interface HelloRepository {
    String get();

    String getMessage();

    String getFormattedMessage();

    void logMessage(String message);
}