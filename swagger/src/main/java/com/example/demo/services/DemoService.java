package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Employee;
import com.example.demo.repositories.DemoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    @Autowired
    DemoRepository empRepository;

    public List<Employee> getAll() {
        return empRepository.getAll();
    }

    public String add(Employee emp) {
        return empRepository.create(emp);
    }
    
    public String edit(Employee emp) {
        return empRepository.edit(emp);
    }

    public String delete(int empId) {
        return empRepository.delete(empId);
    }
}
