package com.example.mdbspringboot.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.example.mdbspringboot.dto.EmployeeDTO;
import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.repository.EmployeeRepository;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import org.springframework.data.domain.Example;

import java.util.HashMap;
import java.util.Map;

import com.example.mdbspringboot.repository.CustomEmployeeRepositoryTwo;

import com.example.mdbspringboot.exception.EmployeeNotFoundException;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
@Primary
public class EmployeeServiceImpl implements EmployeeService {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomEmployeeRepositoryTwo customEmployeeRepositoryTwo;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(String employeeId) throws EmployeeNotFoundException {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("employee not found with id: " + employeeId));
    }

    public Employee create(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeDTO.fromEmployeeDTO(employeeDTO);
        employee.setCreatedAt(LocalDateTime.now());
        return employeeRepository.save(employee);
    }

    public Employee update(EmployeeDTO employeeDTO) {
        if (employeeRepository.existsById(employeeDTO.getId())) {
            Employee employee = EmployeeDTO.fromEmployeeDTO(employeeDTO);
            return employeeRepository.save(employee);
        }
        return null;
    }

    public void delete(String id) {
        if (Objects.nonNull(id) && !id.isBlank()) {
            employeeRepository.deleteById(id);
        }
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

    public String getEmployeesCount(String inputArg) {
        var employeesCount = customEmployeeRepositoryTwo.getEmployeesCount(inputArg);
        var resultMessage = "SERVICE: " + employeesCount;

        LOG.info("\n\n>>>>> EmployeeServiceImpl \n");
        LOG.info("\n\n>>>>> getEmployeesCount: {}\n", resultMessage);

        return resultMessage;
    }

    // here we are using MongoTemplate based repository
    public Employee findById(String id) throws EmployeeNotFoundException {
        Employee employee = customEmployeeRepositoryTwo.findById(id);

        if (Objects.nonNull(employee)) {
            return employee;
        } else {
            throw new EmployeeNotFoundException("employee not found with id : " + id);
        }
    }

    private String getEmployeesCountPrivate(String inputArg) {
        var employeesCount = customEmployeeRepositoryTwo.getEmployeesCount(inputArg);
        var resultMessage = "SERVICE: " + employeesCount;

        LOG.info("\n\n>>>>> EmployeeServiceImpl \n");
        LOG.info("\n\n>>>>> getEmployeesCountPrivate: {}\n", resultMessage);

        return resultMessage;
    }

    @Override
    public Employee getByEmail(String email) throws EmployeeNotFoundException {
        return employeeRepository.findEmployeeByEmail(email)
                .orElseThrow(() -> new EmployeeNotFoundException("employee not found with email: " + email));
    }

    // here we are using our custom repository
    public boolean existsByEmail(String email) {
        return employeeRepository.existsByEmail(email);
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
}