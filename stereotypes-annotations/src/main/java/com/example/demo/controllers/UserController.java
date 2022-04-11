package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/api/users")
@Scope("singleton") // one object per app context
// @Scope("prototype") // one object per request
public class UserController {

    // to demo @Scope annotation
    public UserController() {
        System.out.println("\n *** UserController object created *** \n");
    }

    @Autowired
    // telling the Spring Boot which implementation to use
    // (we could also use @Primary on the specific implementation)
    @Qualifier("userServiceImpl")
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    // http://localhost:8888/api/users/by-id-v1/1
    // if value not provided, 404
    @GetMapping(path = "/by-id-v1/{id}", produces = "application/json")
    public ResponseEntity<Optional<User>> getUserByIdV1(@PathVariable Long id) throws UserNotFoundException {
        Optional<User> user = userService.getById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        } else {
            // as soon as the exception is thrown, it will be handled by
            // UserExceptionHandler class, that must be decorated with
            // @RestControllerAdvice annotation
            throw new UserNotFoundException("user not found with id " + id);
        }
    }

    // http://localhost:8888/api/users/by-id-v2?id=1
    // if value not provided, no exception, no harm
    @GetMapping(path = "/by-id-v2", produces = "application/json")
    public @ResponseBody Optional<User> getUserByIdV2(@RequestParam("id") Long id) {
        return userService.getById(id);
    }

    // curl -X POST http://localhost:8888/api/users/save -d '{"firstName":"Andro"}'
    // -H 'Content-Type: application/json'

    // curl -X POST http://localhost:8888/api/users/save -d
    // "{\"firstName\":\"Andro\"}" -H "Content-Type: application/json"

    @PostMapping("/save")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }
}
