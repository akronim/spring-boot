package com.example.demo.lazy_eager_loading;

import org.springframework.stereotype.Component;

@Component
public class EagerLoadingBean {

    public EagerLoadingBean() {
        System.out.println("\n\n>>>>> EagerLoadingBean object created ..\n\n");
    }
}
