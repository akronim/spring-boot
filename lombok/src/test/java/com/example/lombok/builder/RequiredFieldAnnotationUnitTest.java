package com.example.lombok.builder;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;

public class RequiredFieldAnnotationUnitTest {
    RequiredFieldAnnotation requiredFieldTest;

    @BeforeAll
    public void setUp() {
        requiredFieldTest = RequiredFieldAnnotation.builder("NameField").description("Field Description").build();
    }

    @Test
    public void givenBuilderWithRequiredParameter_thenParameterIsPresent() {
        assertEquals("NameField", requiredFieldTest.getName());
    }

}