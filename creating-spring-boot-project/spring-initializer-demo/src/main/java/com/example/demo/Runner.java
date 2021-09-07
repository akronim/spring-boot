package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    // value of "foo" comes from application.properties file
    @Value("${foo}")
    private String foo;

    @Value("${numbers}")
    private int[] numbers;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello From Bean CommandLine");
        System.out.println(foo);

        for (int number : numbers) {
            System.out.println(number);
        }
    }

}
