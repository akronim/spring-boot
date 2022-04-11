package com.example.demo.controllers;

import com.example.demo.TestBean;
import com.example.demo.config.MailProps;
import com.example.demo.lazy_eager_loading.LazyLoadingBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/demo")
@PropertySource("classpath:custom.properties")
public class DemoController {

    // this class is registered as a bean
    // in configuration class BeanConfig.java
    @Autowired
    private TestBean testBean;

    @Autowired
    private LazyLoadingBean lazyLoadingBean;

    // ******* application.properties I *******
    @Value("${mail.from}")
    private String from;
    @Value("${mail.host}")
    private String host;
    @Value("${mail.port}")
    private String port;
    // ******* application.properties I END *******

    // ******* custom.properties *******
    // you also need to add @PropertySource on the class
    @Value("${message}")
    private String customPropertiesMessage;

    // ******* application.properties II *******
    // here we are using DTO class
    @Autowired
    private MailProps mailProps;
    // ******* application.properties II END *******

    // http://localhost:8888/demo/hello?name=John
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name, Model model) {
        String message = String.format("Hello %s!", name);

        model.addAttribute("message", message);

        return "demo-page";
    }

    @GetMapping("/test-bean")
    public String testBean(Model model) {
        testBean.method();

        model.addAttribute("message", "test-bean");

        return "demo-page";
    }

    @GetMapping("/value-annotation")
    public String valueAnnotation(Model model) {
        model.addAttribute("message",
                from + " " +
                        host + " " +
                        port + " " +
                        customPropertiesMessage);

        return "demo-page";
    }

    @GetMapping("/value-annotation-v2")
    public String valueAnnotationV2(Model model) {
        model.addAttribute("message", mailProps + " " + customPropertiesMessage);

        return "demo-page";
    }
}
