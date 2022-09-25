package com.example.mdbspringboot.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.mdbspringboot.repository.CustomEmployeeRepositoryTwo;
import com.example.mdbspringboot.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
@ActiveProfiles("it")
public class EmployeeServiceSpyTest {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    EmployeeService employeeService;

    @SpyBean
    private EmployeeRepository employeeRepository;

    @SpyBean
    private CustomEmployeeRepositoryTwo customEmployeeRepositoryTwo;

    @DisplayName("getEmployeesCount - spying, but without exploiting it | GIVEN ... " +
            "SHOULD ...")
    @Test
    void getEmployeesCount_x1() {
        String message = employeeService.getEmployeesCount("foobar");
        LOG.info("\n\n::::: getEmployeesCount_x1: {}\n", message);

        assertTrue(message.startsWith("SERVICE: REPOSITORY"));
    }

    @DisplayName("getEmployeesCount - spying and exploiting it | GIVEN ... " +
            "SHOULD ...")
    @Test
    void getEmployeesCount_x2() {
        String mockMessage = "mock message";

        Mockito.when(customEmployeeRepositoryTwo.getEmployeesCount("foobar")).thenReturn(mockMessage);

        String message = employeeService.getEmployeesCount("foobar");
        LOG.info("\n\n::::: getEmployeesCount_x2: {}\n", message);

        String expected = "SERVICE: " + mockMessage;

        assertEquals(expected, message);
    }

    // use Mockito.spy() to mock the same class you are testing
    @DisplayName("getEmployeesCount - spying the same class we are testing | GIVEN ... " +
            "SHOULD ...")
    @Test
    void getEmployeesCount_x3() {
        EmployeeService empService = Mockito.spy(employeeService);

        String expected = "FOO";

        Mockito.doReturn(expected).when(empService).getEmployeesCount("foobar");

        String actual = empService.getEmployeesCount("foobar");
        LOG.info("\n\n::::: getEmployeesCount_x3: {}\n", actual);

        assertEquals(expected, actual);
    }
}