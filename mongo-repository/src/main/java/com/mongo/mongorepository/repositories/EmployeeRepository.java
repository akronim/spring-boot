package com.mongo.mongorepository.repositories;

import java.util.List;

import com.mongo.mongorepository.entities.Employee;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    // query by method names
    List<Employee> findByFirstNameStartingWith(String firstName);

    // query by method names
    List<Employee> findByAddressZipCode(int zipcode);

    // using @Query
    @Query(value = "{'salary':{$gte:?0}}", fields = "{'salary':1, 'firstName':1, 'id':0}")
    List<Employee> getAllBySalaryGTE(float salary);
}
