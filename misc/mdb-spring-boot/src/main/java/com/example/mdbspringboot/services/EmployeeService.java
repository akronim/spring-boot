package com.example.mdbspringboot.services;

import java.util.List;
import java.util.Map;

import com.example.mdbspringboot.dto.EmployeeDTO;
import com.example.mdbspringboot.model.Employee;

import com.example.mdbspringboot.exception.EmployeeNotFoundException;

public interface EmployeeService {
    List<Employee> getAll();

    Employee getById(String employeeId) throws EmployeeNotFoundException;

    Employee create(EmployeeDTO employee);

    Employee update(EmployeeDTO employee);

    void delete(String id);

    Map<String, Object> getByProjects(String[] projects);

    // query by Example Executor
    List<Employee> getAllByExample(Employee employee);

    // query by method names
    List<Employee> getAllByFirstNameStartingWith(String firstName);

    // query by method names
    List<Employee> getAllByDepartment(String department);

    // using @Query
    List<Employee> getAllBySalaryGTE(int salary);

    String getEmployeesCount(String inputArg);

    Employee getByEmail(String email) throws EmployeeNotFoundException;

    // here we are using our custom repository
    boolean existsByEmail(String email);

    // here we are using MongoTemplate based repository
    Employee findById(String id) throws EmployeeNotFoundException;

    Map<String, Object> getAllPaged(int pageNo, int pageSize, String[] fields, String sortBy);
}