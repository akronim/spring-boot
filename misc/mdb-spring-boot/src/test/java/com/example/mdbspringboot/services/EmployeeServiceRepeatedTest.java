package com.example.mdbspringboot.services;

import com.example.mdbspringboot.exception.EmployeeNotFoundException;
import com.example.mdbspringboot.model.Employee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
@ActiveProfiles("it")
public class EmployeeServiceRepeatedTest {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    EmployeeService employeeService;

    @DisplayName("Should repeat 5 times")
    @RepeatedTest(value = 5, name = "Repeating getting employee {currentRepetition} of {totalRepetitions}")
    void findEmployeeByEmail_x1(RepetitionInfo repetitionInfo) throws EmployeeNotFoundException {
        String email = "john@smith.com";

        Employee employee = employeeService.getByEmail(email);

        Assertions.assertEquals(email, employee.getEmail());

        var currentRepetition = repetitionInfo.getCurrentRepetition();

        LOG.info("\n\n>>>>> repetitionInfo: {}\n", currentRepetition);
    }
}