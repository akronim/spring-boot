package com.example.mdbspringboot.services;

import com.example.mdbspringboot.exception.EmployeeNotFoundException;
import com.example.mdbspringboot.model.Employee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration")
public class EmployeeServiceRepeatedTest {

    @Autowired
    EmployeeService employeeService;

    @DisplayName("Should repeat 5 times")
    @RepeatedTest(value = 5, name = "Repeating getting employee {currentRepetition} of {totalRepetitions}")
    public void findEmployeeByEmail_x1() throws EmployeeNotFoundException {
        String email = "john@smith.com";

        Employee employee = employeeService.findEmployeeByEmail(email);

        Assertions.assertEquals(email, employee.getEmail());
    }
}
