package com.example.mdbspringboot.repository;

import com.example.mdbspringboot.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CustomEmployeeRepositoryImpl implements CustomEmployeeRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean existsByEmail(String email) {
        Query query = new Query();

        query.addCriteria(Criteria.where("email").is(email));

        return mongoTemplate.exists(query, Employee.class);
    }
}