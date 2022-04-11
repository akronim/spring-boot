package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.User;

public interface UserService {
    User addUser(User user);

    Optional<User> getById(Long id);

    List<User> getUsers();
}
