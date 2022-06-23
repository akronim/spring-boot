package com.example.mdbspringboot.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.mdbspringboot.dto.EmployeeDTO;
import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.model.Project;
import com.example.mdbspringboot.repository.EmployeeRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// we mock EmployeeRepository here

@SpringBootTest
@ActiveProfiles("it")
class EmployeeServiceMockTest {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private EmployeeService employeeService;

    // Spring Boot's Annotation (different from Mockito's @Mock Annotation)
    // The mock will replace any existing bean of the same type in the application
    // context
    @MockBean
    private EmployeeRepository employeeRepository;

    private Employee emp1;
    private Employee emp2;
    private Employee emp3;
    List<Employee> employees;

    @BeforeEach
    void setUp() {

        emp1 = Employee.builder()
                .id("alex123")
                .firstName("Alex")
                .lastName("Moore")
                .email("alex@moore.com")
                .gender("Male")
                .department("IT")
                .projects(List.of("Project 3", "Project 5", "Project 6"))
                .projects2(List.of(
                        Project.builder().title("Test 1").description("1 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 2").description("2 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 3").description("3 Lorem ipsum dolor sit amet").build()))
                .salary(6350.0)
                .mobile("123 345 6789")
                .createdAt(LocalDateTime.now())
                .build();

        emp2 = Employee.builder()
                .id("ingrid321")
                .firstName("Ingrid")
                .lastName("French")
                .email("ingrid@french.com")
                .gender("Female")
                .department("Production")
                .projects(List.of("Project 2", "Project 3", "Project 4"))
                .projects2(List.of(
                        Project.builder().title("Test 1").description("1 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 2").description("2 Lorem ipsum dolor sit amet").build()))
                .salary(4450.0)
                .mobile("223 345 7789")
                .createdAt(LocalDateTime.now())
                .build();

        emp3 = Employee.builder()
                .id("austin423")
                .firstName("Austin")
                .lastName("Walker")
                .email("austin@walker.com")
                .gender("Male")
                .department("HR")
                .projects(List.of("Project 1", "Project 3", "Project 6"))
                .projects2(List.of(
                        Project.builder().title("Test 1").description("1 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 2").description("2 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 3").description("3 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 4").description("4 Lorem ipsum dolor sit amet").build()))
                .salary(5150.0)
                .mobile("723 545 8789")
                .createdAt(LocalDateTime.now())
                .build();

        employees = List.of(emp1, emp2, emp3);
    }

    @DisplayName("getAll 1 | GIVEN ... SHOULD ...")
    @Test
    void getAll_x1() {
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> employees = employeeService.getAll();

        LOG.info("\n\n>>>>> employees: {}\n", employees);

        assertFalse(CollectionUtils.isEmpty(employees));
        assertEquals(3, employees.size());
    }

    @DisplayName("getById 1 | GIVEN ...  SHOULD ...")
    @Test
    void getById_x1() {
        String employeeId = "alex123";

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(emp1));

        var employee = Assertions.assertDoesNotThrow(() -> {
            return employeeService.getById(employeeId);
        });

        assertTrue(Objects.nonNull(employee));
        assertEquals(employeeId, employee.getId());
        assertTrue(employee.getFirstName().equals("Alex"));
        assertEquals(3, employee.getProjects2().size());
    }

    @DisplayName("create 1 | GIVEN ...  SHOULD ...")
    @Test
    void create_x1() {
        // Mockito uses the equals for argument matching, try using ArgumentMatchers.any
        // for the save / insert method
        when(employeeRepository.save(any(Employee.class))).thenReturn(emp1);

        Employee savedEmployee = employeeService.create(new EmployeeDTO());

        assertNotNull(savedEmployee);
        assertEquals(emp1.getId(), savedEmployee.getId());
    }

    @DisplayName("update 1 | GIVEN ...  SHOULD ...")
    @Test
    void update_x1() {
        // Mockito uses the equals for argument matching, try using ArgumentMatchers.any
        // for the save / insert method
        when(employeeRepository.save(any(Employee.class))).thenReturn(emp1);

        when(employeeRepository.existsById(any(String.class))).thenReturn(true);

        Employee updatedEmployee = employeeService.update(EmployeeDTO.toEmployeeDTO(emp1));

        assertNotNull(updatedEmployee);
        assertEquals(emp1.getId(), updatedEmployee.getId());
    }

    @DisplayName("delete 1 | GIVEN ...  SHOULD ...")
    @Test
    void delete_x1() {
        ArgumentCaptor<String> valueCapture = ArgumentCaptor.forClass(String.class);

        doNothing().when(employeeRepository).deleteById(valueCapture.capture());

        employeeService.delete("foo123");

        assertEquals("foo123", valueCapture.getValue());
    }
}