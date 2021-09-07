package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.example.demo.entities.Employee;

import org.springframework.stereotype.Repository;

@Repository
public class DemoRepository {
    public List<Employee> employees = new ArrayList<Employee>();

    public List<Employee> getAll() {
        return employees;
    }

    public String create(Employee emp) {
        employees.add(emp);
        return "Successfully added";
    }

    public String edit(Employee emp) {
        Stream<Employee> employeeToEdit = employees.stream().filter(e -> e.getEmployeeId() == emp.getEmployeeId());

        employeeToEdit.forEach(x -> {
            x.setName(emp.getName());
            x.setAddress(emp.getAddress());
        });

        return "Successfully updated";
    }

    public String delete(int empId) {
        employees.remove(empId - 1);
        return "Successfully deleted";
    }
}
