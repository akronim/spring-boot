package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// used with application -dev, -stg, -prod .properties files

@Configuration
public class DBConfig {

    @Value("${db.driverClass}")
    private String driverClass;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    @Bean
    // - here we specify which profile to use
    // - must be the same as the profile in application.properties file:
    // spring.profiles.active=stg|prod|dev
    @Profile("dev") 
    public String dataSourceProps() {
        System.out.println("\n\n ================= \n\n");
        System.out.println(driverClass + " : " + url + " : " + username + " : " + password);
        System.out.println("\n\n ================= \n\n");

        return "dataSource connection for ...";
    }
}
