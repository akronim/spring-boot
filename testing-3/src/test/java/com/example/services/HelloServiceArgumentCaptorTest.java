package com.example.services;

import com.example.repository.HelloRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class HelloServiceArgumentCaptorTest {
    @Mock
    private HelloRepository helloRepository;

    @InjectMocks // auto inject helloRepository
    private HelloService helloService = new HelloServiceImpl();

    @Captor
    ArgumentCaptor<String> messageCaptor;

    @Test
    public void getMessage() {
        helloService.logMessage("This is a message.");

        Mockito.verify(helloRepository).logMessage(messageCaptor.capture());

        String capturedMessage = messageCaptor.getValue();

        assertEquals("This is a message. - abc", capturedMessage);
    }

}
