package com.example.mdbspringboot.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URL;
import java.util.List;

import com.example.mdbspringboot.model.Employee;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // for restTemplate
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration")
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    // client - can also be used to send http requests
    private TestRestTemplate restTemplate;

    // bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @Test
    void method_1_x1() throws Exception {
        // when
        ResultActions resultActions = mockMvc
                .perform(get("/home/view-1"));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.valueOf("text/html;charset=UTF-8")))
                .andExpect(content()
                        .string(containsString("<div class=\"view-1-container\">")))
                .andReturn();
    }

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

    @Test
    void addEmployee_x1() throws Exception {
        var employee = List.of(
                new BasicNameValuePair("firstName", "Alex"),
                new BasicNameValuePair("lastName", "Moore"),
                new BasicNameValuePair("email", "alex@moore.com"),
                new BasicNameValuePair("gender", "Male"),
                new BasicNameValuePair("department", "IT"),
                new BasicNameValuePair("projects", "['Project 3', 'Project 5', 'Project 6']"),
                new BasicNameValuePair("salary", "6350.0"),
                // an invalid input
                new BasicNameValuePair("mobile", "123x345x6789"));

        mockMvc.perform(post("/home/save-employee")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(employee))))
                .andExpect(flash().attributeExists("validationErrors"))
                .andExpect(view().name("redirect:/home/view-2"));
    }
}