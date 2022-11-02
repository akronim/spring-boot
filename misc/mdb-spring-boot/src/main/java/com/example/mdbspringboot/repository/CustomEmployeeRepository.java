package com.example.mdbspringboot.repository;

import java.util.List;

import com.example.mdbspringboot.model.Employee;

public interface CustomEmployeeRepository {
    boolean existsByEmail(String email);
    List<Employee> filterAndSort(String regex, String department, Double salaryLt, Double salaryGt);
    List<Employee> getByProjects(String[] projects);
    Employee getByDepartmentAndProjectTitle(String department, String projectTitle);
}