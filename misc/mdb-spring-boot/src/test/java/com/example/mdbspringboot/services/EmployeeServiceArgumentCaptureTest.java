package com.example.mdbspringboot.services;

import com.example.mdbspringboot.repository.CustomEmployeeRepositoryTwo;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration")
public class EmployeeServiceArgumentCaptureTest {
    
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private CustomEmployeeRepositoryTwo customEmployeeRepositoryTwo;

    @Captor
    ArgumentCaptor<String> argCaptor;

    @Test
    public void getEmployeesCount_x1() {
        employeeService.getEmployeesCount("This is an argument");

        Mockito.verify(customEmployeeRepositoryTwo).getEmployeesCount(argCaptor.capture());

        String capturedArg = argCaptor.getValue();

        assertEquals("This is an argument", capturedArg);
    }
}
