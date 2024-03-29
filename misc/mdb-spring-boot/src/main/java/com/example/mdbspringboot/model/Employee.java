package com.example.mdbspringboot.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data // for getters, setters, constructors...
@Document(value = "employees") // so that it can be stored as mongo document
@Builder // now we can create Employee instances using builder API: Employee.builder().firstName("Alex").build()
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id // will be autogenerated
    private String id;
    private String firstName;
    private String lastName;
    // @Indexed(unique = true) => will ensure that email is unique
    // - to enable index, in application.properties we put:
    // spring.data.mongodb.auto-index-creation=true
    @Indexed(unique = true)
    private String email;
    private String gender;
    private String department;
    private List<String> projects;
    @Field("projects_2") // the name of the field in mongodb
    private List<Project> projects2;
    private Double salary;
    private String mobile;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime createdAt;
}