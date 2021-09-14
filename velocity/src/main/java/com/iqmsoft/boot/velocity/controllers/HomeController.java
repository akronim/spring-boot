package com.iqmsoft.boot.velocity.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.boot.velocity.annotation.VelocityLayout;
import com.iqmsoft.boot.velocity.dto.Person;

@Controller
@VelocityLayout("/layout/default.vm") // Default layout page URL in current @Controller
public class HomeController extends BaseController {

	@RequestMapping(method = RequestMethod.GET, value = { "/", "/home" })
	public String home() {
		return "index";
	}

	@VelocityLayout("/layout/layout2.vm") // Overrides default layout @VelocityLayout("/layout/default.vm")
	@RequestMapping(method = RequestMethod.GET, value = "/person")
	public String person(Model model) {
		Person p = new Person();
		p.setFirstName("test");
		p.setLastName("test");

		model.addAttribute(p);
		return "person";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/hello")
	@VelocityLayout("/layout/layout.vm") // Overrides default layout @VelocityLayout("/layout/default.vm")
	public ModelAndView hello(Model model, HttpServletRequest request) {
		List<String> list = new ArrayList<>();
		list.add("Lorem");
		list.add("Ipsum");
		list.add("Dolor");
		list.add("Sit");
		list.add("Amet");

		model.addAttribute("data", list);
		model.addAttribute("request", request.getHeaderNames());

		return new ModelAndView("/hello");
	}
}
