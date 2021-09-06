package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    @RequestMapping(value="/")
    public String greet() {
        return "Hello";
    }
}
