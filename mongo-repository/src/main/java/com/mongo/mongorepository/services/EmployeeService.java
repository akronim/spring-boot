package com.mongo.mongorepository.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongo.mongorepository.entities.Employee;
import com.mongo.mongorepository.repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository empRepository;

    public List<Employee> getAll() {
        return empRepository.findAll();
    }

    public Employee addEmployee(Employee emp) {
        return empRepository.insert(emp);
    }

    public Employee update(Employee emp) {
        return empRepository.save(emp);
    }

    public void delete(String id) {
        empRepository.deleteById(id);
    }

    public Map<String, Object> getAllInPage(int pageNo, int pageSize, String sortBy) {
        Map<String, Object> response = new HashMap<String, Object>();

        Sort sort = Sort.by(sortBy);
        Pageable page = PageRequest.of(pageNo, pageSize, sort);
        Page<Employee> empPage = empRepository.findAll(page);

        response.put("data", empPage.getContent());
        response.put("Total no of pages", empPage.getTotalPages());
        response.put("Total no of elements", empPage.getTotalElements());
        response.put("Current page no", empPage.getNumber());

        return response;
    }

    // query by Example Executor
    public List<Employee> getAllByExample(Employee emp) {
        Example<Employee> example = Example.of(emp);

        // ExampleMatcher employeeMatcher = ExampleMatcher.matchingAll().withIgnoreCase("lastName", "firstName")
        //         .withIgnorePaths("id").withNullHandler(ExampleMatcher.NullHandler.INCLUDE)
        //         .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        List<Employee> employees = empRepository.findAll(example);

        return employees;
    }

    // query by method names
    public List<Employee> getAllByFirstName(String firstName) {
        return empRepository.findByFirstNameStartingWith(firstName);
    }

    // query by method names
    public List<Employee> getAllByZipCode(int zipcode) {
        return empRepository.findByAddressZipCode(zipcode);
    }

    // using @Query
    public List<Employee> getAllBySalaryGTE(float salary) {
        return empRepository.getAllBySalaryGTE(salary);
    }

}
