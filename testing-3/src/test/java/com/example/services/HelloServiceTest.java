package com.example.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class HelloServiceTest {

    @Autowired
    HelloService helloService;

    @DisplayName("Test Spring @Autowired Integration")
    @Test
    void testGet() {
        assertEquals("Hello JUnit 5", helloService.get());
    }

    @Test
    public void getMessage() {
        String message = helloService.getMessage();
        assertEquals(message, "Lorem ipsum dolor sit amet.");
    }

    @Test
    public void getFormattedMessage() {
        String formattedMessage = helloService.getFormattedMessage();
        assertEquals(formattedMessage, "Hi there! Lorem ipsum dolor sit amet.");
    }

}