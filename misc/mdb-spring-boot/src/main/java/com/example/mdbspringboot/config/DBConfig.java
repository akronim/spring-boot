package com.example.mdbspringboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

// used with application -dev, -stg, -prod ... .properties files

@Configuration
@Slf4j
public class DBConfig {

    @Value("${spring.data.mongodb.uri}")
    private String url;

    @Bean
    public String dataSourceProps() {
        log.info("\n\n>>>>> Data Source Connection For: " + url + "\n\n");
        return url;
    }
}