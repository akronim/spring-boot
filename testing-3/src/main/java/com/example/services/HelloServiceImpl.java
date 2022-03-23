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

    public boolean runInGround(String location) {
        if (location.equals("ground")) {
            System.out.println("This runs in the " + location);
            return true;
        } else {
            System.out.println("This doesn't run in the " + location);
            return false;
        }
    }

    public boolean isPlay() {
        if (this.runInGround("ground")) {
            System.out.println("The person plays.");
            return true;
        } else {
            System.out.println("The person doesn't play");
            return false;
        }
    }

}
