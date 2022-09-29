package com.example.mdbspringboot.repository;

public interface CustomEmployeeRepository {
    boolean existsByEmail(String email);
    public void filterAndSort();
}