package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

// here we map fields from application.properties prefixed with mail

@ConfigurationProperties(prefix = "mail")
@Component
@Data
public class MailProps {

    private String from;
    private String host;
    private String port;
}
