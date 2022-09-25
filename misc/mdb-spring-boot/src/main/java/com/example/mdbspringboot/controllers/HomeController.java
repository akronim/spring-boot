package com.example.mdbspringboot.controllers;

import com.alibaba.boot.velocity.annotation.VelocityLayout;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.RequiredArgsConstructor;

import org.springframework.ui.Model;

import java.util.List;

import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.props.MailProps;
import com.example.mdbspringboot.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mdbspringboot.exception.EmployeeNotFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.mdbspringboot.dto.EmployeeDTO;
import java.util.Objects;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import com.example.mdbspringboot.validation.EmployeeValidator;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.WebDataBinder;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Value;
import java.util.Locale;

import org.springframework.context.annotation.PropertySource;

import com.example.mdbspringboot.other.LazyLoadingBean;

@Controller
@RequestMapping("/home")
@VelocityLayout("/layouts/layout-1.vm") // default layout page URL for this controller
@RequiredArgsConstructor
@PropertySource("classpath:custom.properties") // new class annotation
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private static final String EMPLOYEE_DTO = "employeeDTO";

	private final EmployeeValidator employeeValidator; // works with: @RequiredArgsConstructor

	@Autowired
	EmployeeService employeeService;

	// ******* application.properties I *******
	// we bind values from the application.properties file to local variables
	@Value("${mail.from}")
	private String from;
	@Value("${mail.host}")
	private String host;
	@Value("${mail.port}")
	private String port;
	// ******* application.properties I END *******

	// ******* custom.properties *******
	// you also need to add @PropertySource on the class
	@Value("${message}")
	private String customPropertiesMessage;

	// ******* application.properties II *******
	// here we are using DTO class
	@Autowired
	private MailProps mailProps;
	// ******* application.properties II END *******

	@Autowired
    private LazyLoadingBean lazyLoadingBean;

	// The value of @InitBinder is the name of model attribute
	@InitBinder(value = EMPLOYEE_DTO)
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(employeeValidator);
	}

	// http://localhost:8102/mdb-spring-boot/home/view-one
	@RequestMapping(method = RequestMethod.GET, value = { "/", "/view-one" })
	public String method_1(Model model) {
		model.addAttribute("pageTitle", "VIEW 1");
		return "home/view-1";
	}

	// @RequestMapping(method = RequestMethod.GET, value = "/view-two")
	@GetMapping("/view-two") // another syntax
	public String method_2(Model model) {
		List<Employee> employees = employeeService.getAllByFirstNameStartingWith("Al");
		model.addAttribute("employees", employees);
		return "home/view-2";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/view-three")
	@VelocityLayout("/layouts/layout-2.vm") // overrides default layout
	public ModelAndView method_3(Locale locale) {
		var mav = new ModelAndView("home/view-3");
		mav.addObject("message", from + " " + host + " " + port + " " + customPropertiesMessage);

		mav.addObject("mailProps", mailProps);

		logger.info("\n>>>>>> Welcome home! The client locale is {}.", locale);

		return mav;
	}

	// http://localhost:8102/mdb-spring-boot/home/employees-by-department?department=IT
	@RequestMapping(path = "/employees-by-department", produces = "application/json; charset=UTF-8")
	@ResponseBody // it binds a method return value to the web response body, it is not
					// interpreted as a view name
	public List<Employee> getEmployeesByDepartment(@RequestParam(name = "department") String department) {
		return employeeService.getAllByDepartment(department);
	}

	@GetMapping(value = "/employee/edit")
	public String renderEdit(@RequestParam(name = "id", required = false) String id, Model model,
			final RedirectAttributes redirectAttributes) {
		if (Objects.isNull(id) || id.equals("")) {
			model.addAttribute(EMPLOYEE_DTO, new EmployeeDTO());
			return "home/view-4";
		}

		try {
			var employeeDTO = EmployeeDTO.toEmployeeDTO(employeeService.getById(id));
			model.addAttribute(EMPLOYEE_DTO, employeeDTO);
			return "home/view-4";
		} catch (EmployeeNotFoundException | IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
			// this is redirection to the controller, not returning a view
			return "redirect:/home/view-two";
		}
	}

	// validation example
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String save(final RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute(EMPLOYEE_DTO) EmployeeDTO employeeDTO, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("errorMessage", "Failed to save");
			model.addAttribute("validationErrors", result.getAllErrors());
			model.addAttribute(EMPLOYEE_DTO, employeeDTO);

			return "home/view-4"; // returning a view
		}

		employeeService.create(employeeDTO);

		redirectAttributes.addFlashAttribute("successMessage", "employee successfully saved");

		// this is redirection to the controller, not returning a view
		return "redirect:/home/view-two";
	}

	@RequestMapping(value = "/ajax-save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> ajaxSave(final RedirectAttributes redirectAttributes,
			@Valid @RequestBody EmployeeDTO employeeDTO, BindingResult result) {

		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getAllErrors());
		}

		employeeService.create(employeeDTO);

		redirectAttributes.addFlashAttribute("successMessage", "employee successfully saved");

		return new ResponseEntity<>(HttpStatus.OK);
	}

	// used in: footer.vm
	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("WikipediaLinkLabel", "Wikipedia");
		model.addAttribute("WikipediaLink", "https://en.wikipedia.org/wiki/Java_(programming_language)");
	}
}