package com.mongo.testmongo.controllers;

import java.util.List;

import com.mongo.testmongo.entities.Employee;
import com.mongo.testmongo.services.EmployeeService;

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

    @PostMapping("/add")
    public String create(@RequestBody Employee emp) {
        return empService.add(emp);
    }

    @PutMapping("/edit")
    public String edit(@RequestBody Employee emp) {
        return empService.edit(emp);
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam String id) {
        return empService.delete(id);
    }

    @DeleteMapping("/delete-v2/{id}")
    public String deleteNo2(@PathVariable String id) {
        return empService.delete(id);
    }
}
