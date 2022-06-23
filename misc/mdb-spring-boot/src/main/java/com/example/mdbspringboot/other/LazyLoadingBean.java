package com.example.mdbspringboot.other;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class LazyLoadingBean {

    public LazyLoadingBean() {
        System.out.println("\n\n>>>>> LazyLoadingBean object created ..\n\n");
    }
}
