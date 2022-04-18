package com.example.demo;

import java.util.List;

import com.example.demo.entities.Employee;
import com.example.demo.repositories.EmployeeRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(EmployeeRepository repository, MongoTemplate mongoTemplate) {
		return args -> {
			String email = "john@smith.com";

			Employee employee = new Employee("John", "Smith", email, "Male", "Finance",
					List.of("Project 3", "Project 5", "Project 6"), 5500.0);

			repository.findEmployeeByEmail(email).ifPresentOrElse(s -> {
				System.out.println(s + " already exists");
			}, () -> {
				System.out.println("Inserting employee " + employee);
				repository.insert(employee);
			});
		};
	}

	// Query query = new Query();
	// query.addCriteria(Criteria.where("email").is(email));
	// List<Student> students = mongoTemplate.find(query, Student.class);
}
