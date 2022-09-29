package com.example.mdbspringboot.repository;

import com.example.mdbspringboot.model.Employee;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;

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

    public void filterAndSort() {
        Query query = new Query();

        query.fields().include("id").include("firstName").include("salary");

        List<Criteria> criteria = new ArrayList<>();

        criteria.add(Criteria.where("firstName").regex("^A"));
        criteria.add(Criteria.where("department").is("HR"));
        criteria.add(Criteria.where("salary").lt(6000).gt(3000));

        // sorting by salary
        query.with(Sort.by(Sort.Direction.DESC, "salary"));

        query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));

        var employees = mongoTemplate.find(query, Employee.class);

        employees.forEach(e -> System.out.println(e.toString()));
    }

}