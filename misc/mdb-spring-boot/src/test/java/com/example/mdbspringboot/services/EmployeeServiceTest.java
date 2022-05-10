package com.example.mdbspringboot.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.repository.EmployeeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration")
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        // we will mock EmployeeRepository

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

        String firstName = "Al";
        Mockito.when(employeeRepository.findByFirstNameStartingWith(firstName))
                .thenReturn(List.of(employee1));

    }

    @DisplayName("getAllByFirstNameStartingWith | GIVEN ... " +
            "SHOULD ...")
    @Test
    void getAllByFirstNameStartingWith_x1() {
        String firstName = "Al";

        List<Employee> employees = employeeService.getAllByFirstNameStartingWith(firstName);

        assertTrue(!employees.isEmpty());

        employees.forEach(x -> {
            assertTrue(x.getFirstName().startsWith(firstName));
        });
    }
}
