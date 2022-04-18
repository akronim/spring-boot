package com.example.demo.controllers;

import com.example.demo.entities.Employee;
import com.example.demo.services.EmployeeService;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // here we will use MongoTemplate based repository
    @GetMapping("/{id}")
    public Employee findEmployee(@PathVariable final String id) {
        return employeeService.findById(id);
    }

    @PostMapping("/add-one")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PostMapping("/add-multiple")
    public List<Employee> addEmployees(@RequestBody final List<Employee> employees) {
        return employeeService.saveAll(employees);
    }

    @PutMapping("/")
    public Employee update(@RequestBody Employee employee) {
        return employeeService.update(employee);
    }

    @PatchMapping("/")
    public Employee patch(@RequestBody Employee employee) {
        return employeeService.patch(employee);
    }

    // ...delete?id=6
    @DeleteMapping("/delete")
    public String delete(@RequestParam String id) {
        employeeService.delete(id);
        return "Deleted";
    }

    // ...delete-v2/6
    @DeleteMapping("/delete-v2/{id}")
    public String deleteNo2(@PathVariable String id) {
        employeeService.delete(id);
        return "Deleted";
    }

    // ...delete-v3?id=6
    @DeleteMapping("/delete-v3")
    public String deleteNo3(@PathParam("id") String id) {
        employeeService.delete(id);
        return "Deleted";
    }

    @GetMapping("/page")
    public Map<String, Object> getAllPaged(@RequestParam(name = "pageno", defaultValue = "0") int pageNo,
            @RequestParam(name = "pagesize", defaultValue = "10") int pageSize,
            @RequestParam(name = "fields", defaultValue = "firstName,lastName,salary") String[] fields,
            @RequestParam(name = "sortby", defaultValue = "salary") String sortBy) {
        return employeeService.getAllPaged(pageNo, pageSize, fields, sortBy);
    }

    @GetMapping("/projects")
    public Map<String, Object> getByProjects(@RequestParam(name = "projects", required = true) String[] projects) {
        return employeeService.getByProjects(projects);
    }

    // query by Example Executor
    @GetMapping("/example")
    public List<Employee> getAllByExample(@RequestBody Employee employee) {
        return employeeService.getAllByExample(employee);
    }

    // query by method names
    @GetMapping("/first-name")
    public List<Employee> getAllByFirstName(@RequestParam(name = "firstName") String firstName) {
        return employeeService.getAllByFirstNameStartingWith(firstName);
    }

    // query by method names
    @GetMapping("/department")
    public List<Employee> getAllByDepartment(@RequestParam(name = "department") String department) {
        return employeeService.getAllByDepartment(department);
    }

    // using @Query
    @GetMapping("/salary")
    public List<Employee> getAllBySalaryGTE(@RequestParam(name = "salary") int salary) {
        return employeeService.getAllBySalaryGTE(salary);
    }

    // here we will use our custom repository
    @PatchMapping("/{employeeId}")
    public void testMethod(@PathVariable final String employeeId, @RequestBody final Employee employee) {
        employeeService.testMethod(employeeId, employee);
    }
}
