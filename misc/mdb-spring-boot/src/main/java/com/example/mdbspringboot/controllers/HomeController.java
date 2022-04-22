package com.example.mdbspringboot.controllers;

import com.alibaba.boot.velocity.annotation.VelocityLayout;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/home")
@VelocityLayout("/layouts/layout-1.vm") // Default layout page URL
public class HomeController {

    // http://localhost:8102/mdb-spring-boot/home/view-1
    @RequestMapping(method = RequestMethod.GET, value = { "/", "/view-1" })
    public String method_1(Model model) {
        model.addAttribute("pageTitle", "VIEW 1");
        return "home/view-1";
    }
}
