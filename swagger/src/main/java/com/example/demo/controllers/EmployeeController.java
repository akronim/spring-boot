package com.example.demo.controllers;

import java.util.List;

import com.example.demo.entities.Employee;
import com.example.demo.services.DemoService;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

// @RestController(value = "FooBar")
@RestController
@RequestMapping(value = "/emp")
@Api(tags = { "Employee Management" }) // Swagger
public class EmployeeController {

    @Autowired
    DemoService empService;

    @GetMapping("/get/all")
    @ApiOperation(value = "Get all employees details") // Swagger
    // @ApiResponse(code=200, message = "OK OK") // Swagger
    @ApiResponses(value = {
        @ApiResponse(code=200, message = "OK OK"),
        @ApiResponse(code=404, message = "Not Found Not Found"),
    })
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
    public String delete(@RequestParam int empId) {
        return empService.delete(empId);
    }

    @DeleteMapping("/delete-v2/{empId}")
    public String deleteNo2(@PathVariable int empId) {
        return empService.delete(empId);
    }
}
