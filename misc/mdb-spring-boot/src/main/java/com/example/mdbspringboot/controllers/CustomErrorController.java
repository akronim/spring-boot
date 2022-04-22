package com.example.mdbspringboot.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    String error(HttpServletRequest request) {
        Integer status = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        String message = exception == null ? "" : exception.getMessage();

        return "<div>" + HttpStatus.valueOf(status) + "</div>" + "<div>" + message + "</div>";
    }
}
