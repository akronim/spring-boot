package com.example.mdbspringboot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CoreInterceptor implements HandlerInterceptor {

    /**
     * to perform some action on requests sent to the controller
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("Request intercepted");
        return true;
    }

    /**
     * to perform some action on responses sent to the client like addng attributes
     * to the model
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) {
        log.info("Response intercepted");
    }
}
