package com.mongo.mongorepository.controllers;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import com.mongo.mongorepository.entities.Employee;
import com.mongo.mongorepository.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    EmployeeService empService;

    @GetMapping("/")
    public List<Employee> getAll() {
        return empService.getAll();
    }

    @PostMapping("/")
    public Employee addEmployee(@RequestBody Employee emp) {
        return empService.addEmployee(emp);
    }

    @PutMapping("/")
    public Employee update(@RequestBody Employee emp) {
        return empService.update(emp);
    }

    // ...delete?id=6
    @DeleteMapping("/delete")
    public void delete(@RequestParam String id) {
        empService.delete(id);
    }

    // ...delete-v2/6
    @DeleteMapping("/delete-v2/{id}")
    public void deleteNo2(@PathVariable String id) {
        empService.delete(id);
    }

    // ...delete-v3?id=6
    @DeleteMapping("/delete-v3")
    public void deleteNo3(@PathParam("id") String id) {
        empService.delete(id);
    }

    @GetMapping("/page")
    public Map<String, Object> getAllInPage(@RequestParam(name = "pageno", defaultValue = "0") int pageNo,
            @RequestParam(name = "pagesize", defaultValue = "2") int pageSize,
            @RequestParam(name = "sortby", defaultValue = "id") String sortBy) {
        return empService.getAllInPage(pageNo, pageSize, sortBy);
    }

    // query by Example Executor
    @GetMapping("/example")
    public List<Employee> getAllByExample(@RequestBody Employee emp) {
        return empService.getAllByExample(emp);
    }

    // query by method names
    @GetMapping("/firstname")
    public List<Employee> getAllByFirstName(@RequestParam(name = "firstname") String firstName) {
        return empService.getAllByFirstName(firstName);
    }

    // query by method names
    @GetMapping("/zipcode")
    public List<Employee> getAllByZipCode(@RequestParam(name = "zipcode") int zipcode) {
        return empService.getAllByZipCode(zipcode);
    }

    // using @Query
    @GetMapping("/salary")
    public List<Employee> getAllBySalaryGTE(@RequestParam(name = "salary") float salary) {
        return empService.getAllBySalaryGTE(salary);
    }
}
