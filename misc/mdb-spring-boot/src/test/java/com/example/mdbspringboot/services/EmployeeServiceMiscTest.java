package com.example.mdbspringboot.services;

import com.example.mdbspringboot.exception.EmployeeNotFoundException;
import com.example.mdbspringboot.model.Employee;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("it")
public class EmployeeServiceMiscTest {

    @Autowired
    EmployeeService employeeService;

    @BeforeAll
    public static void setupAll() {
        System.out.println("\n~~~~~ Should Print BEFORE ALL Tests");
    }

    @BeforeEach
    public void setup() {
        System.out.println("\n..... Should Print BEFORE EACH Test");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("\n..... Should Print AFTER EACH Test");
    }

    @AfterAll
    public static void tearDownAll() {
        System.out.println("\n~~~~~ Should Print AFTER ALL Tests");
    }

    @Test
    @DisplayName("Should Run only on LINUX")
    @EnabledOnOs(value = OS.LINUX, disabledReason = "Should Run only on LINUX")
    public void getByEmail_x1() throws EmployeeNotFoundException {
        String email = "john@smith.com";
        Employee employee = employeeService.getByEmail(email);
        Assertions.assertEquals(email, employee.getEmail());
    }

    @Test
    @DisplayName("Should Run only on WINDOWS")
    @EnabledOnOs(value = OS.WINDOWS, disabledReason = "Should Run only on WINDOWS")
    public void getByEmail_x2() throws EmployeeNotFoundException {
        String email = "john@smith.com";
        Employee employee = employeeService.getByEmail(email);
        Assertions.assertEquals(email, employee.getEmail());
    }

    @Nested
    class NestedTests {
        @Test
        @DisplayName("Should Run only on Developer Machine")
        public void getByEmail_x1() throws EmployeeNotFoundException {
            Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));

            String email = "john@smith.com";
            Employee employee = employeeService.getByEmail(email);
            Assertions.assertEquals(email, employee.getEmail());
        }

        @Test
        @DisplayName("Test Should Be Disabled")
        @Disabled
        public void shouldBeDisabled() {
            throw new RuntimeException("Test Should Not be executed");
        }
    }
}