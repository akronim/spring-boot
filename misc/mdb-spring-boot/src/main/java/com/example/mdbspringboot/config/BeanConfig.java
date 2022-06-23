package com.example.mdbspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.mdbspringboot.other.DemoComponent;

@Configuration
public class BeanConfig {

    @Bean
    public DemoComponent demoComponent() {
        return new DemoComponent();
    }

}
