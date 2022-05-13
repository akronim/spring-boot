package com.example.mdbspringboot.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.example.mdbspringboot.model.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration")
class EmployeeServiceTest {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private EmployeeService employeeService;

    @DisplayName("getAllByFirstNameStartingWith | GIVEN ... " +
            "SHOULD ...")
    @Test
    void getAllByFirstNameStartingWith_x1() {
        String firstName = "Al";

        List<Employee> employees = employeeService.getAllByFirstNameStartingWith(firstName);

        assertTrue(!employees.isEmpty());

        LOG.info("\n\n>>>>> employees count: {}\n", employees.size());

        employees.forEach(x -> {
            assertTrue(x.getFirstName().startsWith(firstName));
        });
    }
}
