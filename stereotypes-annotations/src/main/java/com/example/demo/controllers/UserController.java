package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    // http://localhost:8888/api/users/1
    @GetMapping(path = "/{id}", produces = "application/json")
    public @ResponseBody User getUser(@PathVariable Long id) {
        return userService.getById(id);
    }
}
