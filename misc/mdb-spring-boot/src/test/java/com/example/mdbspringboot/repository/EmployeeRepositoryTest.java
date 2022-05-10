package com.example.mdbspringboot.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.example.mdbspringboot.model.Employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataMongoTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.3.3")
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void dataSetup() {
        Employee employee1 = Employee.builder()
                .firstName("Alex")
                .lastName("Moore")
                .email("alex@moore.com")
                .gender("Male")
                .department("IT")
                .projects(List.of("Project 3", "Project 5", "Project 6"))
                .salary(6350.0)
                .mobile("123 345 6789")
                .build();

        employeeRepository.save(employee1);
    }

    @Test
    void testFindByFirstNameStartingWith() {

        String firstName = "Al";

        List<Employee> employees = employeeRepository.findByFirstNameStartingWith(firstName);

        assertTrue(!employees.isEmpty());

        employees.forEach(x -> {
            assertTrue(x.getFirstName().startsWith(firstName));
        });
    }
}
