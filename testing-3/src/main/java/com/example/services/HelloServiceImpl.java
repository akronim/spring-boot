package com.example.services;

import com.example.repository.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Autowired
    HelloRepository helloRepository;

    @Override
    public String get() {
        return helloRepository.get();
    }

    public String getMessage() {
        return helloRepository.getMessage();
    }

    public String getFormattedMessage() {
        return helloRepository.getFormattedMessage();
    }

    public void logMessage(String message) {
        message += " - abc";
        helloRepository.logMessage(message);
    }

}
