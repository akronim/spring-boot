package com.example.mdbspringboot.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.repository.EmployeeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration")
class EmployeeServiceMockTwoTest {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @InjectMocks // will auto-inject EmployeeService
    private EmployeeService employeeService = new EmployeeServiceImpl();
    
    @Mock
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

        LOG.info("\n\n>>>>> employees count: {}\n", employees.size());

        employees.forEach(x -> {
            assertTrue(x.getFirstName().startsWith(firstName));
        });
    }
}
