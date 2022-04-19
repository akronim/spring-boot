package com.example.mdbspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class MdbSpringBootApplication implements CommandLineRunner {

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(MdbSpringBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String email = "john@smith.com";
		Employee employee = new Employee("John", "Smith", email, "Male", "Finance",
				List.of("Project 3", "Project 5", "Project 6"), 5500.0);
		employeeRepository.findEmployeeByEmail(email).ifPresentOrElse(s -> {
			System.out.println("\n>>>>>> " + s + " already exists");
		}, () -> {
			System.out.println("\n>>>>>> Inserting employee " + employee);
			employeeRepository.insert(employee);
		});
	}
}