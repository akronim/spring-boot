package com.example.demo.repositories;

import java.util.List;

import com.example.demo.entities.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

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
