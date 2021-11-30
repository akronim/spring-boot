package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

import com.example.entities.Input;

@Controller
public class MainController {

    @ResponseBody
    @GetMapping("/")
    public String hello() {
        return "Hello Controller";
    }

    @PostMapping("/validate-1")
    public String validate1(@RequestParam(value = "channel") String channel,
            @ModelAttribute @Valid Input input, BindingResult result) {

        if (result.hasErrors()) {
            return "has errors";
        }
        return "no errors";
    }

}
