package com.example.mdbspringboot.repository;

import java.util.List;

import com.example.mdbspringboot.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

@Repository
public class CustomEmployeeRepositoryTwo {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Employee> findAll() {
        return mongoTemplate.findAll(Employee.class);
    }

    public void saveAll(final List<Employee> employees) {
        mongoTemplate.insertAll(employees);
    }

    public Employee findById(final String employeeId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(employeeId));

        return mongoTemplate.findOne(query, Employee.class);
    }
}