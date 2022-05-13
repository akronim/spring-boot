package com.example.mdbspringboot.controllers;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import com.alibaba.boot.velocity.annotation.VelocityLayout;
import com.example.mdbspringboot.dto.EmployeeDTO;
import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.services.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/home")
@VelocityLayout("/layouts/layout-1.vm") // default layout page URL for this controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	EmployeeService employeeService;

	// we insert the view-3-message property from the application.properties file
	// into the message attribute
	@Value("${view-3-message}")
	private String message;

	// http://localhost:8102/mdb-spring-boot/home/view-1
	@RequestMapping(method = RequestMethod.GET, value = { "/", "/view-1" })
	public String method_1(Model model) {
		model.addAttribute("pageTitle", "VIEW 1");
		return "home/view-1";
	}

	// @RequestMapping(method = RequestMethod.GET, value = "/view-2")
	@GetMapping("/view-2")
	public String method_2(Model model) {
		List<Employee> employees = employeeService.getAllByFirstNameStartingWith("Al");

		model.addAttribute("employees", employees);

		return "home/view-2";
	}

	@RequestMapping(value = "/save-employee", method = RequestMethod.POST)
	public String addEmployee(final RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute("employee") EmployeeDTO employee, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("validationErrors", bindingResult.getAllErrors());
			redirectAttributes.addFlashAttribute("employee", employee);
		} else {
			employeeService.addEmployee(employee);
		}

		return "redirect:/home/view-2";
	}

	@RequestMapping(value = "/save-employee-ajax", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public RedirectView addEmployeeAjax(final RedirectAttributes redirectAttributes, @Valid @RequestBody EmployeeDTO employee,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("validationErrors", bindingResult.getAllErrors());
			redirectAttributes.addFlashAttribute("employee", employee);
		} else {
			employeeService.addEmployee(employee);
		}

		RedirectView redirectView = new RedirectView();
		redirectView.setExposeModelAttributes(true);
		redirectView.setUrl("/mdb-spring-boot/home/view-2");
		return redirectView;
	}

	@VelocityLayout("/layouts/layout-2.vm") // overrides default layout
	@RequestMapping(method = RequestMethod.GET, value = "/view-3")
	public ModelAndView method_3(Locale locale) {
		var mav = new ModelAndView("home/view-3");
		mav.addObject("message", message);

		logger.info("\n>>>>>> Welcome home! The client locale is {}.", locale);

		return mav;
	}

	// see: footer.vm
	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("WikipediaLinkLabel", "Wikipedia");
		model.addAttribute("WikipediaLink", "https://en.wikipedia.org/wiki/Java_(programming_language)");
	}

	// http://localhost:8102/mdb-spring-boot/home/employees-by-department?department=IT
	@RequestMapping(path = "/employees-by-department", produces = "application/json; charset=UTF-8")
	@ResponseBody // it binds a method return value to the web response body, it is not
					// interpreted as a view name
	public List<Employee> getEmployeesByDepartment(@RequestParam(name = "department") String department) {
		return employeeService.getAllByDepartment(department);
	}
}
