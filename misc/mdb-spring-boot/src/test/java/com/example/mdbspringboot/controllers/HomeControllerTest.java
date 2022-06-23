package com.example.mdbspringboot.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("it")
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void method_1_x1() throws Exception {
        // when
        ResultActions resultActions = mockMvc
                .perform(get("/home/view-one"));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("text/html;charset=UTF-8")))
                .andExpect(content().string(containsString("<div class=\"view-1-container\">")))
                .andReturn();
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
                // an invalid input + no projects2
                new BasicNameValuePair("mobile", "123x345x6789"));

        mockMvc.perform(post("/home/save")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(employee))))
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(2))
                .andExpect(model().attributeExists("validationErrors"))
                .andExpect(view().name("home/view-4"));
    }
}