package com.example.mdbspringboot.repository;

import java.util.List;

import com.example.mdbspringboot.model.Employee;

public interface CustomEmployeeRepositoryTwo {
    List<Employee> findAll();

    void saveAll(final List<Employee> employees);

    Employee findById(final String employeeId);

    String getEmployeesCount(String inputArg);
}
