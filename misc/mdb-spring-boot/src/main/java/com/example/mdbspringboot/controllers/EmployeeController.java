package com.example.mdbspringboot.controllers;

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

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.example.mdbspringboot.dto.EmployeeDTO;
import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.services.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public List<Employee> getAllEmployees() {
        LOG.info("\n>>>>> Getting all employees.\n");
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable String employeeId) {
        LOG.info("\n>>>>> Getting employee with ID: {}.\n", employeeId);

        // here we are using MongoTemplate based repository
        return employeeService.findById(employeeId);
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> addEmployee(@RequestBody @Valid EmployeeDTO employee) {
        LOG.info("\n>>>>> Saving employee.\n");
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }

    @PostMapping("/create-multiple")
    public List<Employee> addEmployees(@RequestBody final List<Employee> employees) {
        LOG.info("\n>>>>> Saving employees.\n");
        return employeeService.addEmployees(employees);
    }

    @PutMapping("/")
    public Employee update(@RequestBody Employee employee) {
        LOG.info("\n>>>>> Updating employee.\n");
        return employeeService.update(employee);
    }

    @PatchMapping("/")
    public Employee patch(@RequestBody Employee employee) {
        LOG.info("\n>>>>> Patching employee.\n");
        return employeeService.patch(employee);
    }

    // ...delete?id=6
    @DeleteMapping("/delete")
    public String delete(@RequestParam String id) {
        LOG.info("\n>>>>> Deleting employee with ID: {}.\n", id);
        employeeService.delete(id);
        return "Deleted";
    }

    // ...delete-v2/6
    @DeleteMapping("/delete-v2/{id}")
    public String deleteNo2(@PathVariable String id) {
        LOG.info("\n>>>>> Deleting employee with ID: {}.\n", id);
        employeeService.delete(id);
        return "Deleted";
    }

    // ...delete-v3?id=6
    @DeleteMapping("/delete-v3")
    public String deleteNo3(@PathParam("id") String id) {
        LOG.info("\n>>>>> Deleting employee with ID: {}.\n", id);
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

    // here we are using our custom repository
    @PatchMapping("/{employeeId}")
    public void testMethod(@PathVariable final String employeeId, @RequestBody final Employee employee) {
        employeeService.testMethod(employeeId, employee);
    }

}