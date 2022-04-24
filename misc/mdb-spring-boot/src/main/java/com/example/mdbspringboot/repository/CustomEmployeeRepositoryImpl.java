package com.example.mdbspringboot.repository;

import com.example.mdbspringboot.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomEmployeeRepositoryImpl implements CustomEmployeeRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void testMethod(String employeeId, Employee employee) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(employee.getEmail()));
        List<Employee> employees = mongoTemplate.find(query, Employee.class);

        if (employees.isEmpty()) {
            System.out.println("Employee does not exist");
        } else {
            System.out.println(employee + " already exists");
        }
    }
}