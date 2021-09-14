package com.iqmsoft.boot.velocity.controllers;

import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class BaseController {

    @ModelAttribute("pageTitle")
    public String pageTitle() {
        return "Demo";
    }

    @ModelAttribute("linkLabel")
    public String linkLabel() {
        return "Wikipedia";
    }

    @ModelAttribute("link")
    public String link() {
        return "https://en.wikipedia.org/wiki/Main_Page";
    }
}
