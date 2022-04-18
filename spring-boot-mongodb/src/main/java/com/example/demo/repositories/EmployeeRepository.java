package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;
import com.example.demo.entities.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String>, CustomEmployeeRepository {

    @Query(value = "{ 'projects': { $elemMatch : { $in: ?0 } } }", fields = "{'firstName': 1, 'lastName': 1, 'projects': 1}")
    List<Employee> getBy(String[] projects);

    // queries from method names
    Optional<Employee> findEmployeeByEmail(String email);

    // query by method names
    List<Employee> findByFirstNameStartingWith(String firstName);

    // using @Query
    @Query(value = "{'salary':{$gte:?0}}", fields = "{'salary':1, 'firstName':1, 'lastName':1, '_id':0}")
    List<Employee> getAllBySalaryGTE(int salary);

    List<Employee> findByDepartment(String department);

    // if we want to use raw mongodb queries
    // @Query("")
    // void test();
}

// to get the JSON converter to ignore null values - application.properties
// spring.jackson.default-property-inclusion=non-null


