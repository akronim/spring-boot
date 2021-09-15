package com.iqmsoft.boot.velocity.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.boot.velocity.annotation.VelocityLayout;
import com.iqmsoft.boot.velocity.dto.User;
import com.iqmsoft.boot.velocity.services.UserService;

@Controller
@VelocityLayout("/layout/layout1.vm") // Default layout page URL in current @Controller
public class HomeController extends BaseController {

	@Autowired
    UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = { "/", "/view-1" })
	public String method_1() {
		return "view-1";
	}

	@VelocityLayout("/layout/layout2.vm") // Overrides default layout
	@RequestMapping(method = RequestMethod.GET, value = "/view-2")
	public String method_2(Model model) {
		List<User> users = userService.getAll();

		model.addAttribute("users", users);

		// User user = new User();
		// user.setFirstName("test");
		// user.setLastName("test");

		// model.addAttribute(user);
		return "view-2";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/view-3")
	@VelocityLayout("/layout/layout3.vm") // Overrides default layout
	public ModelAndView method_3(Model model, HttpServletRequest request) {
		List<String> list = new ArrayList<>();
		list.add("Lorem");
		list.add("Ipsum");
		list.add("Dolor");
		list.add("Sit");
		list.add("Amet");

		model.addAttribute("data", list);
		model.addAttribute("request", request.getHeaderNames());

		return new ModelAndView("/view-3");
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String addUser(@ModelAttribute User newUser) {

		userService.add(newUser);
		
		return "redirect:/view-2";
	}
}
