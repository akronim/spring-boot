package com.example.mdbspringboot.services;

import java.util.List;
import java.util.Map;

import com.example.mdbspringboot.dto.EmployeeDTO;
import com.example.mdbspringboot.model.Employee;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee findOne(String employeeId);

    Employee addEmployee(EmployeeDTO employee);

    List<Employee> addEmployees(List<Employee> employees);

    Employee update(Employee employee);

    Employee patch(Employee employeeUpdateRequest);

    void delete(String id);

    Map<String, Object> getAllPaged(int pageNo, int pageSize, String[] fields, String sortBy);

    Map<String, Object> getByProjects(String[] projects);

    // query by Example Executor
    List<Employee> getAllByExample(Employee employee);

    // query by method names
    List<Employee> getAllByFirstNameStartingWith(String firstName);

    // query by method names
    List<Employee> getAllByDepartment(String department);

    // using @Query
    List<Employee> getAllBySalaryGTE(int salary);

    // here we are using our custom repository
    void testMethod(String employeeId, Employee employee);

    // here we are using MongoTemplate based repository
    Employee findById(String id);
}
