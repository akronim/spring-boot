package com.example.demo;

// instead of doing this, we register this class as a bean in BeanConfig
// @Component  
public class TestBean {
    public void method() {
        System.out.println("TestBean method logic excuted ");
    }
}
