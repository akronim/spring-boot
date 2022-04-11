package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplV2 implements UserService {
    @Override
    public Optional<User> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User addUser(User user) {
        return null;
    }
}
