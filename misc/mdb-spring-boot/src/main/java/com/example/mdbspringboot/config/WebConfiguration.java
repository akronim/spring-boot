package com.example.mdbspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.example.mdbspringboot.interceptor.CoreInterceptor;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * All web related config. Serves as replacement for @EnableWebMvc.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    public CoreInterceptor coreInterceptor() {
        return new CoreInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(coreInterceptor());
    }
}