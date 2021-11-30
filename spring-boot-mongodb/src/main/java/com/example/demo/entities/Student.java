package com.example.demo.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data // for getters, setters, constructors...
@Document // so that it can be stored as mongo document
public class Student {
    @Id // will be autogenerated
    private String id;
    private String firstName;
    private String lastName;
    // @Indexed(unique = true) => will ensure that email is unique
    // to enable index, in application.properties we put:
    // spring.data.mongodb.auto-index-creation=true
    @Indexed(unique = true) 
    private String email;
    private Gender gender;
    private Address address;
    private List<String> favouriteSubjects;
    private BigDecimal totalSpentInBooks;
    // @Field("dateCreated")
    private LocalDateTime createdAt;

    public Student(String firstName, String lastName, String email, Gender gender, Address address,
            List<String> favouriteSubjects, BigDecimal totalSpentInBooks, LocalDateTime createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.favouriteSubjects = favouriteSubjects;
        this.totalSpentInBooks = totalSpentInBooks;
        this.createdAt = createdAt;
    }
}