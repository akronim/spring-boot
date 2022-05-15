package com.example.mdbspringboot.services;

import com.example.mdbspringboot.exception.EmployeeNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration")
public class EmployeeServiceExceptionTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    @DisplayName("Should throw an Exception if an employee does not exist")
    public void findEmployeeByEmail_x1() {
        String email = "foo@bar.com";

        Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.findEmployeeByEmail(email);
        });
    }

    @Test
    @DisplayName("Should not throw an Exception if an employee does exist")
    public void findEmployeeByEmail_x2() {
        String email = "john@smith.com";

        var employee = Assertions.assertDoesNotThrow(() -> {
            return employeeService.findEmployeeByEmail(email);
        });

        Assertions.assertEquals(email, employee.getEmail());
    }
}
