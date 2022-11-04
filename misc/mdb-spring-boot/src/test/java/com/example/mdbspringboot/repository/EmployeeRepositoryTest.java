package com.example.mdbspringboot.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.model.Project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee emp1;
    private Employee emp2;
    private Employee emp3;
    List<Employee> employees;

    @BeforeEach
    void setUp() {

        employeeRepository.deleteAll();

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
                .projects(List.of("Project 2", "Project 3"))
                .projects2(List.of(
                        Project.builder().title("Test 1").description("1 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 2").description("2 Lorem ipsum dolor sit amet").build()))
                .salary(4450.0)
                .mobile("223 345 7789")
                .createdAt(LocalDateTime.now())
                .build();

        emp3 = Employee.builder()
                .firstName("Austin")
                .lastName("Walker")
                .email("austin@walker.com")
                .gender("Male")
                .department("HR")
                .projects(List.of("Project 1"))
                .projects2(List.of(
                        Project.builder().title("Test 1").description("1 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 2").description("2 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 3").description("3 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 4").description("4 Lorem ipsum dolor sit amet").build()))
                .salary(5150.0)
                .mobile("723 545 8789")
                .createdAt(LocalDateTime.now())
                .build();

        employees = List.of(emp1, emp2);

        employeeRepository.saveAll(employees);
    }

    @AfterEach
    public void tearDown() {
        employeeRepository.deleteAll();
    }

    @DisplayName("findAll 1 | GIVEN ...  SHOULD ...")
    @Test
    void findAll_x1() {
        List<Employee> employees = employeeRepository.findAll();

        LOG.info("\n\n>>>>> employees: {}\n", employees);

        assertFalse(CollectionUtils.isEmpty(employees));

        assertEquals(2, employees.size());

        assertTrue(employees.stream().anyMatch(x -> x.getFirstName().equals("Alex") && x.getProjects2().size() == 3));
    }

    @DisplayName("findById 1 | GIVEN ...  SHOULD ...")
    @Test
    void findById_x1() {
        String employeeId = "alex123";

        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        assertTrue(Objects.nonNull(employee));
        assertEquals(employeeId, employee.getId());
        assertTrue(employee.getFirstName().equals("Alex"));
        assertEquals(3, employee.getProjects2().size());
    }

    @DisplayName("create 1 | GIVEN ...  SHOULD ...")
    @Test
    void create_x1() {
        assertFalse(employeeRepository.findAll().stream().anyMatch(p -> p.getEmail().equals(emp3.getEmail())));

        Employee savedEmployee = employeeRepository.save(emp3);

        assertNotNull(savedEmployee);
        assertEquals(emp3.getEmail(), savedEmployee.getEmail());

        assertTrue(employeeRepository.findAll().stream().anyMatch(p -> p.getEmail().equals(emp3.getEmail())));
    }

    @DisplayName("update 1 | GIVEN ...  SHOULD ...")
    @Test
    void update_x1() {
        assertTrue(employeeRepository.existsById(emp1.getId()));

        emp1.setDepartment("foobar");

        Employee updatedEmployee = employeeRepository.save(emp1);

        assertNotNull(updatedEmployee);
        assertEquals("foobar", updatedEmployee.getDepartment());
    }

    @DisplayName("delete 1 | GIVEN ...  SHOULD ...")
    @Test
    void delete_x1() {
        assertTrue(employeeRepository.existsById(emp1.getId()));

        employeeRepository.deleteById(emp1.getId());

        assertFalse(employeeRepository.existsById(emp1.getId()));
    }

    // MongoTemplate
    @DisplayName("filterAndSort 1 | GIVEN ...  SHOULD ...")
    @Test
    void filterAndSort_x1() {
        var employees = employeeRepository.filterAndSort("^A", "IT", 8000.0, 4000.0);

        assertEquals(1, employees.size());

    }

    @DisplayName("getByProjectsAndDepartments 1 | GIVEN ...  SHOULD ...")
    @Test
    void getByProjectsAndDepartments_x1() {
        var employees = employeeRepository.getByProjectsAndDepartments(new String[] { "Project 2", "Project 3" }, new String[] { "IT", "Production" });

        assertEquals(2, employees.size());

        assertTrue(employees.get(0).getProjects().contains("Project 2") || employees.get(0).getProjects().contains("Project 3"));
    }

    @DisplayName("getByDepartmentAndProjectTitle 1 | GIVEN ...  SHOULD ...")
    @Test
    void getByDepartmentAndProjectTitle_x1() {
        var employee = employeeRepository.getByDepartmentAndProjectTitle("IT", "Test 1");

        assertNotNull(employee);
        assertEquals(1, employee.getProjects2().size());
    }

}