package com.example.services;

import com.example.repository.HelloRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HelloServiceMockTest {

    @Mock
    private HelloRepository helloRepository;

    @InjectMocks // auto inject helloRepository
    private HelloService helloService = new HelloServiceImpl();

    @BeforeEach
    void setMockOutput() {
        when(helloRepository.get()).thenReturn("abc 123");
        when(helloRepository.getMessage()).thenReturn("foo 123");
    }

    @DisplayName("Test Mock helloService + helloRepository")
    @Test
    void testGet() {
        assertEquals("abc 123", helloService.get());
    }

    @Test
    public void getMessage() {
        String message = helloService.getMessage();
        assertEquals(message, "foo 123");
    }

    @Test
    public void getFormattedMessage() {
        String formattedMessage = helloService.getFormattedMessage();
        assertNull(formattedMessage);
    }

}