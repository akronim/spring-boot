package com.example.lombok.builder.defaultvalue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BuilderWithDefaultValueUnitTest {

    @Test
    public void givenBuilderWithDefaultValue_ThanDefaultValueIsPresent() {
        Pojo build = new Pojo().toBuilder()
            .build();
        assertEquals("foo", build.getName());
        assertTrue(build.isOriginal());
    }

    @Test
    public void givenBuilderWithDefaultValue_NoArgsWorksAlso() {
        Pojo build = new Pojo().toBuilder()
            .build();
        Pojo pojo = new Pojo();
        assertEquals(build.getName(), pojo.getName());
        assertTrue(build.isOriginal() == pojo.isOriginal());
    }

}
