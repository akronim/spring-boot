package com.example.mdbspringboot.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document(collection = "sportNames")
public class SportName {
    @Id
    private String id;
    private Set<String> names;
}
