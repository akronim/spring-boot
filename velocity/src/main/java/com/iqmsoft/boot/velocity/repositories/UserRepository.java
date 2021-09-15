package com.iqmsoft.boot.velocity.repositories;

import java.util.ArrayList;
import java.util.List;

import com.iqmsoft.boot.velocity.dto.User;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public List<User> users = new ArrayList<User>();

    public List<User> getAll() {
        return users;
    }

    public String create(User user) {
        users.add(user);
        return "Successfully added";
    }
}

