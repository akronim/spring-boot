package com.example.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

import com.example.repository.HelloRepository;

@SpringBootTest
public class HelloServiceSpyTest {

    @Autowired
    HelloService helloService;

    @SpyBean
    private HelloRepository helloRepository;

    @DisplayName("Test Spring @Autowired Integration")
    @Test
    void testGet() {
        assertEquals("Hello JUnit 5", helloService.get());
    }

    @Test
    public void getMessage_x1() {
        String mockMessage = "mock message.";

        Mockito.when(helloRepository.getMessage()).thenReturn(mockMessage);

        String message = helloService.getMessage();
        assertEquals(message, mockMessage);
    }

    @Test
    public void getMessage_x2() {
        Mockito.doReturn(null).when(helloRepository).getMessage();

        String message = helloService.getMessage();
        assertEquals(null, message);
    }

    @Test
    public void getFormattedMessage() {
        String formattedMessage = helloService.getFormattedMessage();
        assertEquals(formattedMessage, "Hi there! Lorem ipsum dolor sit amet.");
    }

    // use Mockito.spy() to mock the same class you are testing
    @Test
    public void playTest() {
        HelloService helloService2 = Mockito.spy(helloService);

        Mockito.doReturn(true).when(helloService2).runInGround("ground");

        assertEquals(true, helloService2.isPlay());
    }

    // 
    @Test
    public void playTest_x2() {
        helloService.isPlay();

        var result = ReflectionTestUtils.invokeMethod(helloService, "runInGround", "ground" );

        assertEquals(result, true);
        assertEquals(true, helloService.isPlay());
    }
}