package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/demo")
public class DemoController {

    // http://localhost:8888/demo/hello?name=John
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name, Model model) {
        String message = String.format("Hello %s!", name);

        model.addAttribute("message", message);

        return "demo-page";
    }
}
