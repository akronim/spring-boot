package com.mongo.mongo2.services;

import java.util.Date;
import java.util.List;

import com.mongo.mongo2.entities.Employee;
import com.mongo.mongo2.repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository empRepository;

    public List<Employee> getAll() {
        return empRepository.find();
    }

    public List<Employee> getBySalary(int salary) {
        return empRepository.getBySalary(salary);
    }

    public List<Employee> getByFirstName(String firstName) {
        return empRepository.getByFirstName(firstName);
    }

    public Employee save(Employee emp) {
        emp.setJoiningDate(new Date());
        return empRepository.save(emp);
    }

    public Employee update(Employee emp) {
        return empRepository.save(emp);
    }

    public long delete(String id) {
        return empRepository.delete(id);
    }

}
