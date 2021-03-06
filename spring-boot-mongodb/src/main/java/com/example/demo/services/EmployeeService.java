package com.example.demo.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.entities.Employee;
import com.example.demo.repositories.EmployeeDAO;
import com.example.demo.repositories.EmployeeRepository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeDAO employeeDAO;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.insert(employee);
    }

    public List<Employee> saveAll(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    public Employee update(Employee employee) {
        if (employeeRepository.existsById(employee.getId())) {
            return employeeRepository.save(employee);
        }
        return employee;
    }

    public Employee patch(Employee employeeUpdateRequest) {
        if (employeeRepository.existsById(employeeUpdateRequest.getId())) {
            Optional<Employee> optionalEmployee = employeeRepository.findById(employeeUpdateRequest.getId());

            Employee employee = optionalEmployee.get();

            employee.setFirstName(employeeUpdateRequest.getFirstName());
            employee.setLastName(employeeUpdateRequest.getLastName());
            employee.setEmail(employeeUpdateRequest.getEmail());
            employee.setGender(employeeUpdateRequest.getGender());
            employee.setDepartment(employeeUpdateRequest.getDepartment());
            employee.setSalary(employeeUpdateRequest.getSalary());
            employee.setProjects(employeeUpdateRequest.getProjects());

            return employeeRepository.save(employee);
        }
        return employeeUpdateRequest;
    }

    public void delete(String id) {
        employeeRepository.deleteById(id);
    }

    public Map<String, Object> getAllPaged(int pageNo, int pageSize, String[] fields, String sortBy) {
        Map<String, Object> response = new HashMap<String, Object>();

        Sort sort = Sort.by(sortBy);
        Pageable page = PageRequest.of(pageNo, pageSize, sort);
        Page<Employee> employeePage = employeeRepository.findAll(page);

        response.put("data", employeePage.getContent());
        response.put("Total no of pages", employeePage.getTotalPages());
        response.put("Total no of elements", employeePage.getTotalElements());
        response.put("Current page no", employeePage.getNumber());

        return response;
    }

    public Map<String, Object> getByProjects(String[] projects) {
        Map<String, Object> response = new HashMap<String, Object>();

        List<Employee> listOfEmployees = employeeRepository.getBy(projects);
        response.put("data", listOfEmployees);
        response.put("Total no of employees", listOfEmployees.size());

        return response;
    }

    // query by Example Executor
    public List<Employee> getAllByExample(Employee employee) {
        Example<Employee> example = Example.of(employee);

        // ExampleMatcher employeeMatcher =
        // ExampleMatcher.matchingAll().withIgnoreCase("lastName", "firstName")
        // .withIgnorePaths("id").withNullHandler(ExampleMatcher.NullHandler.INCLUDE)
        // .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        List<Employee> employees = employeeRepository.findAll(example);

        return employees;
    }

    // query by method names
    public List<Employee> getAllByFirstNameStartingWith(String firstName) {
        return employeeRepository.findByFirstNameStartingWith(firstName);
    }

    // query by method names
    public List<Employee> getAllByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    // using @Query
    public List<Employee> getAllBySalaryGTE(int salary) {
        return employeeRepository.getAllBySalaryGTE(salary);
    }

    // here we are using MongoTemplate based repository
    public Employee findById(String id) {
        return employeeDAO.findById(id);
    }

    // here we are using our custom repository
    public void testMethod(String employeeId, Employee employee) {
        employeeRepository.testMethod(employeeId, employee);
    }

}
