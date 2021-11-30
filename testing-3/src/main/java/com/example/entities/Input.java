package com.example.entities;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Input {
    @NotBlank
    private String firstName;
    private String lastName;
    private Integer fooNumber;
}
