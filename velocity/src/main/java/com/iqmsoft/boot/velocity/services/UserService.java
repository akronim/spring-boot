package com.iqmsoft.boot.velocity.services;

import java.util.List;

import com.iqmsoft.boot.velocity.dto.User;
import com.iqmsoft.boot.velocity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public String add(User user) {
        return userRepository.create(user);
    }
}
