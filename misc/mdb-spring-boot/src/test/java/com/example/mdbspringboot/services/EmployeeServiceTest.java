package com.example.mdbspringboot.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.example.mdbspringboot.model.Employee;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.CollectionUtils;

// we do not mock EmployeeRepository here

@SpringBootTest
@ActiveProfiles("it")
class EmployeeServiceTest {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private EmployeeService employeeService;

    @DisplayName("getAll 1 | GIVEN ...  SHOULD ...")
    @Test
    void getAll_x1() {

        List<Employee> employees = employeeService.getAll();

        assertFalse(CollectionUtils.isEmpty(employees));

        LOG.info("\n\n>>>>> employees count: {}\n", employees.size());
    }

    @DisplayName("getAllByFirstNameStartingWith 1 | GIVEN ... SHOULD ...")
    @Test
    void getAllByFirstNameStartingWith_x1() {
        String firstName = "Al";

        List<Employee> employees = employeeService.getAllByFirstNameStartingWith(firstName);

        assertFalse(CollectionUtils.isEmpty(employees));

        employees.forEach(x -> {
            assertTrue(x.getFirstName().startsWith(firstName));
        });
    }
}