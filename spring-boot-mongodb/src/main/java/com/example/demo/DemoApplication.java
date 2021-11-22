package com.example.demo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.entities.Address;
import com.example.demo.entities.Gender;
import com.example.demo.entities.Student;
import com.example.demo.repositories.StudentRepository;

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
	CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
		return args -> {
			Address address = new Address("England", "London", "NE9");
			String email = "john@smith.com";

			Student student = new Student("John", "Smith", email, Gender.MALE, address,
					List.of("maths", "english", "it"), BigDecimal.TEN, LocalDateTime.now());

			repository.findStudentByEmail(email).ifPresentOrElse(s -> {
				System.out.println(s + " already exists");
			}, () -> {
				System.out.println("Inserting student " + student);
				repository.insert(student);
			});
		};
	}

	// Query query = new Query();
	// query.addCriteria(Criteria.where("email").is(email));
	// List<Student> students = mongoTemplate.find(query, Student.class);
}
