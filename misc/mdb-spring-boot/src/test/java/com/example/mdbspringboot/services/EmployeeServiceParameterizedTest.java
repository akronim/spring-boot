package com.example.mdbspringboot.services;

import java.util.Arrays;
import java.util.List;

import com.example.mdbspringboot.exception.EmployeeNotFoundException;
import com.example.mdbspringboot.model.Employee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration")
public class EmployeeServiceParameterizedTest {

    @Autowired
    EmployeeService employeeService;

    @ParameterizedTest
    @DisplayName("Should ...")
    @ValueSource(strings = { "john@smith.com", "mrobertelli3@tinyurl.com", "shakonsen7@dyndns.org" })
    public void findEmployeeByEmail_x1(String email) throws EmployeeNotFoundException {
        Employee employee = employeeService.findEmployeeByEmail(email);
        Assertions.assertEquals(email, employee.getEmail());
    }

    @ParameterizedTest
    @DisplayName("Should ... (CSV Source Case)")
    @CsvSource({ "john@smith.com", "mrobertelli3@tinyurl.com", "shakonsen7@dyndns.org" })
    public void findEmployeeByEmail_x2(String email) throws EmployeeNotFoundException {
        Employee employee = employeeService.findEmployeeByEmail(email);
        Assertions.assertEquals(email, employee.getEmail());
    }

    @ParameterizedTest
    @DisplayName("Should ... (CSV File Source Case)")
    @CsvFileSource(resources = "/employees-emails.csv")
    public void findEmployeeByEmail_x3(String email) throws EmployeeNotFoundException {
        Employee employee = employeeService.findEmployeeByEmail(email);
        Assertions.assertEquals(email, employee.getEmail());
    }

    @ParameterizedTest
    @DisplayName("Should ... (Method Source Case)")
    @MethodSource("employeeEmailList")
    public void findEmployeeByEmail_x4(String email) throws EmployeeNotFoundException {
        Employee employee = employeeService.findEmployeeByEmail(email);
        Assertions.assertEquals(email, employee.getEmail());
    }

    private static List<String> employeeEmailList() {
        return Arrays.asList("john@smith.com", "mrobertelli3@tinyurl.com", "shakonsen7@dyndns.org");
    }
}
