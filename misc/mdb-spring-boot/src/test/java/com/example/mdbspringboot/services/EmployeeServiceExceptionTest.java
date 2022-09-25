package com.example.mdbspringboot.services;

import com.example.mdbspringboot.exception.EmployeeNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("it")
public class EmployeeServiceExceptionTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    @DisplayName("Should throw an Exception if an employee does not exist")
    void findEmployeeByEmail_x1() {
        String email = "foo@bar.com";

        Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.getByEmail(email);
        });

        EmployeeNotFoundException thrown = Assertions.assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.getByEmail(email));

        Assertions.assertEquals("employee not found with email: foo@bar.com", thrown.getMessage());
    }

    @Test
    @DisplayName("Should not throw an Exception if an employee does exist")
    public void findEmployeeByEmail_x2() {
        String email = "john@smith.com";

        var employee = Assertions.assertDoesNotThrow(() -> {
            return employeeService.getByEmail(email);
        });

        Assertions.assertEquals(email, employee.getEmail());
    }
}