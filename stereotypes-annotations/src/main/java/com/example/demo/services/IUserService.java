package com.example.demo.services;

import com.example.demo.entities.User;

public interface IUserService {
    User getById(Long id);
}
