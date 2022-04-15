package com.example.mongotemplate.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import com.example.mongotemplate.entities.Employee;
import com.example.mongotemplate.services.EmployeeService;

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

    @GetMapping("/get/all")
    public List<Employee> getAll() {
        return empService.getAll();
    }

    @GetMapping("/get/by-salary")
    public List<Employee> getBySalary(@PathParam("salary") int salary) {
        return empService.getBySalary(salary);
    }

    @GetMapping("/get/by-firstname")
    public List<Employee> getByFirstName(@PathParam("firstName") String firstName) {
        return empService.getByFirstName(firstName);
    }

    @PostMapping("/add")
    public Employee save(@RequestBody Employee emp) {
        return empService.save(emp);
    }

    @PutMapping("/edit")
    public Employee edit(@RequestBody Employee emp) {
        return empService.update(emp);
    }

    @DeleteMapping("/delete")
    public long delete(@RequestParam String id) {
        return empService.delete(id);
    }

    @DeleteMapping("/delete-v2/{id}")
    public long deleteNo2(@PathVariable String id) {
        return empService.delete(id);
    }
}
