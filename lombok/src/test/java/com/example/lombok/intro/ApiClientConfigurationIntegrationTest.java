package com.example.lombok.intro;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.example.lombok.intro.ApiClientConfiguration.ApiClientConfigurationBuilder;

import org.junit.jupiter.api.Test;

public class ApiClientConfigurationIntegrationTest {

    @Test
    public void givenAnnotatedConfiguration_thenCanBeBuiltViaBuilder() {
        ApiClientConfiguration config =
            new ApiClientConfigurationBuilder()
                .host("api.server.com")
                .port(443)
                .useHttps(true)
                .connectTimeout(15_000L)
                .readTimeout(5_000L)
                .username("myusername")
                .password("secret")
            .build();

        assertEquals(config.getHost(), "api.server.com");
        assertEquals(config.getPort(), 443);
        assertEquals(config.isUseHttps(), true);
        assertEquals(config.getConnectTimeout(), 15_000L);
        assertEquals(config.getReadTimeout(), 5_000L);
        assertEquals(config.getUsername(), "myusername");
        assertEquals(config.getPassword(), "secret");
    }

    @Test
    public void givenAnnotatedConfiguration_thenHasLoggerInstance() throws NoSuchFieldException {
        Field loggerInstance = ApiClientConfiguration.class.getDeclaredField("log");
        int modifiers = loggerInstance.getModifiers();
        assertTrue(Modifier.isPrivate(modifiers));
        assertTrue(Modifier.isStatic(modifiers));
        assertTrue(Modifier.isFinal(modifiers));
    }

}
