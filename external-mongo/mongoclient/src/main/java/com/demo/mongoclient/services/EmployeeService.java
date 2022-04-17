package com.demo.mongoclient.services;

import java.util.List;

import com.demo.mongoclient.entities.Employee;
import com.demo.mongoclient.repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository empRepository;

    public List<Employee> getAll() {
        return empRepository.getAll();
    }

    public String add(Employee emp) {
        return empRepository.create(emp);
    }
    
    public String edit(Employee emp) {
        return empRepository.edit(emp);
    }

    public String delete(String id) {
        return empRepository.delete(id);
    }
}
