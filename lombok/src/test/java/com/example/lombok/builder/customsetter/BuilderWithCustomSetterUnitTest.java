package com.example.lombok.builder.customsetter;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.Test;

public class BuilderWithCustomSetterUnitTest {

    @Test
    public void givenBuilderWithCustomSetter_TestTextOnly() {
        Message message = Message.builder()
            .sender("user@somedomain.com")
            .recipient("someuser@otherdomain.com")
            .text("How are you today?")
            .build();
    }

    @Test
    public void givenBuilderWithCustomSetter_TestFileOnly() {
        Message message = Message.builder()
            .sender("user@somedomain.com")
            .recipient("someuser@otherdomain.com")
            .file(new File("/path/to/file"))
            .build();
    }

    @Test
    public void givenBuilderWithCustomSetter_TestTextAndFile() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            Message message = Message.builder()
            .sender("user@somedomain.com")
            .recipient("someuser@otherdomain.com")
            .text("How are you today?")
            .file(new File("/path/to/file"))
            .build();
        });
    }
}
