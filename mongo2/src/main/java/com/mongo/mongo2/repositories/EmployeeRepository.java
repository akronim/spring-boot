package com.mongo.mongo2.repositories;

import java.util.List;

import com.mongo.mongo2.entities.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Employee> find() {
        return mongoTemplate.findAll(Employee.class);
    }

    public List<Employee> getBySalary(int salary) {
        Query query = new Query(Criteria.where("salary").gte(salary));
        // query.fields().exclude(field)...
        return mongoTemplate.find(query, Employee.class);
    }

    public List<Employee> getByFirstName(String firstName) {
        Query query = new Query(Criteria.where("firstName").regex("^" + firstName));
        return mongoTemplate.find(query, Employee.class);
    }

    public Employee save(Employee emp) {
        return mongoTemplate.save(emp);
    }

    public long delete(String id) {
        Employee employeeToDelete = mongoTemplate.findById(id, Employee.class);
        return mongoTemplate.remove(employeeToDelete).getDeletedCount();
    }

}
