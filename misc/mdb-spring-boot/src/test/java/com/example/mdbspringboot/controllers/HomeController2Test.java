package com.example.mdbspringboot.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URL;

import com.example.mdbspringboot.model.Employee;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // for restTemplate
@AutoConfigureMockMvc
@ActiveProfiles("it")
public class HomeController2Test {

    @Autowired
    // can also be used as client to send http requests
    private TestRestTemplate restTemplate;

    // bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @Test
    public void getEmployeesByDepartment_x1() throws Exception {

        String department = "IT";

        ResponseEntity<Employee[]> response = restTemplate
                .getForEntity(
                        new URL("http://localhost:" + port
                                + "/mdb-spring-boot/home/employees-by-department?department="
                                + department)
                                .toString(),
                        Employee[].class);

        Employee[] employees = response.getBody();
        assertTrue(employees.length > 0);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.valueOf("application/json;charset=UTF-8"),
                response.getHeaders().getContentType());

        for (Employee employee : employees) {
            assertTrue(employee.getDepartment().equals(department));
        }
    }
}