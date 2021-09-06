package com.example.demo.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "Employee")
@Scope("prototype")
public class DemoController {
    @RequestMapping(value="/")
    public String greet() {
        return "Hello";
    }
}
