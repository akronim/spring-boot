package com.example.mdbspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// ReflectionTestUtils
// - used to set the non-public fields, invoke non-public methods, and inject dependencies

@SpringBootTest
@ActiveProfiles("it")
public class EmployeeServiceReflectionTestUtilsTest {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    EmployeeService employeeService;

    @Test
    public void getEmployeesCountPrivate_x1() {

        var result = ReflectionTestUtils.invokeMethod(employeeService, "getEmployeesCountPrivate", "Lorem");
        LOG.info("\n\n::::: getEmployeesCountPrivate_x1: {}\n", result);

        assertTrue(result.toString().startsWith("SERVICE: REPOSITORY - Lorem | COUNT:"));
    }
}