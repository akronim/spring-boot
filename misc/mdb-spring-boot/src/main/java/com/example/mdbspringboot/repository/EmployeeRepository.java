package com.example.mdbspringboot.repository;

import com.example.mdbspringboot.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Query;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String>, CustomEmployeeRepository {
    @Query(value = "{ 'projects': { $elemMatch : { $in: ?0 } } }", fields = "{'firstName': 1, 'lastName': 1, 'projects': 1}")
    List<Employee> getBy(String[] projects);

    // query by method names
    List<Employee> findByFirstNameStartingWith(String firstName);

    List<Employee> findByDepartment(String department);

    // using @Query
    @Query(value = "{'salary':{$gte:?0}}", fields = "{'salary':1, 'firstName':1, 'lastName':1, '_id':0}")
    List<Employee> getAllBySalaryGTE(int salary);

    // queries from method names
    Optional<Employee> findEmployeeByEmail(String email);
}

// to get the JSON converter to ignore null values =>
// - add this to the application.properties file:
// spring.jackson.default-property-inclusion=non-null