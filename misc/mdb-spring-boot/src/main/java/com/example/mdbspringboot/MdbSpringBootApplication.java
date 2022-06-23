package com.example.mdbspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;
import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.model.Project;
import com.example.mdbspringboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class MdbSpringBootApplication implements CommandLineRunner {

	@Autowired
	private EmployeeRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(MdbSpringBootApplication.class, args);
	}

	// db.employees.deleteOne( { "email" : "john@smith.com" } );

	@Override
	public void run(String... args) throws Exception {
		String email = "john@smith.com";

		Employee employee = Employee.builder()
				.firstName("John")
				.lastName("Smith")
				.email(email)
				.gender("Male")
				.department("Finance")
				.projects(List.of("Project 3", "Project 5", "Project 6"))
				.projects2(List.of(
						Project.builder().title("Test 1").description("1 Lorem ipsum dolor sit amet").build(),
						Project.builder().title("Test 2").description("2 Lorem ipsum dolor sit amet").build()))
				.salary(5500.0)
				.mobile("123 345 6789")
				.createdAt(LocalDateTime.now())
				.build();

		repository.findAll().stream().filter(e -> e.getEmail().equals(email)).findFirst().ifPresentOrElse(s -> {
			System.out.println("\n>>>>>> " + s + " already exists");
		}, () -> {
			System.out.println("\n>>>>>> Inserting employee " + employee);
			repository.insert(employee);
		});
	}
}