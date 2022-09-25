package com.example.mdbspringboot.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private String id;
    @NotBlank(message = "project title shouldn't be blank")
    private String title;
    @NotBlank(message = "project description shouldn't be blank")
    private String description;
}