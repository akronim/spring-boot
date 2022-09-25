package com.example.mdbspringboot.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.mdbspringboot.dto.EmployeeDTO;
import com.example.mdbspringboot.exception.EmployeeNotFoundException;
import com.example.mdbspringboot.model.Employee;

@Service
public class EmployeeServiceImplV2 implements EmployeeService {

    @Override
    public List<Employee> getAll() {
        return null;
    }

    @Override
    public Employee getById(String employeeId) {
        return null;
    }

    @Override
    public Employee create(EmployeeDTO employee) {
        return null;
    }

    @Override
    public Employee update(EmployeeDTO employee) {
        return null;
    }

    @Override
    public void delete(String id) {
        
    }

    @Override
    public Map<String, Object> getByProjects(String[] projects) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Employee> getAllByExample(Employee employee) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Employee> getAllByFirstNameStartingWith(String firstName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Employee> getAllByDepartment(String department) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Employee> getAllBySalaryGTE(int salary) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getEmployeesCount(String inputArg) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Employee findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Employee getByEmail(String email) throws EmployeeNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Map<String, Object> getAllPaged(int pageNo, int pageSize, String[] fields, String sortBy) {
        // TODO Auto-generated method stub
        return null;
    }
}